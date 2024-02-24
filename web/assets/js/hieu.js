
document.addEventListener('DOMContentLoaded', function () {
    const quantityInputs = document.querySelectorAll('.input_number');
    const incrementButtons = document.querySelectorAll('.input_number_increment_in');
    const decrementButtons = document.querySelectorAll('.input_number_decrement_de');
    const totalPrices = document.querySelectorAll('.total_price');
    const couponOption = document.querySelector('.coupon');
    
    let totalPriceSum = 0;
    
    quantityInputs.forEach(function (input) {
        input.addEventListener('input', function () {
            updateTotalPrice(this);
        });
    });

    incrementButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            const input = this.parentNode.querySelector('.input_number');
            input.stepUp();
            updateTotalPrice(input);
        });
    });

    decrementButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            const input = this.parentNode.querySelector('.input_number');
            input.stepDown();
            updateTotalPrice(input);
        });
    });


    function updateTotalPrice(input) {
        const row = input.closest('tr');
        const priceText = row.querySelector('.price_text').innerText;
        const price = parseFloat(priceText.substring(0, priceText.length - 1));
        const quantity = parseFloat(input.value);
        const totalPrice = price * quantity;
        row.querySelector('.total_price').innerText = totalPrice.toFixed(2) + 'VND';

        const stockText = row.querySelector('.stock_text').innerText;
        const stock = parseInt(stockText);
        const remainingStock = stock - quantity;
        row.querySelector('.remaining_stock').innerText = remainingStock + ' product left';

        let totalPriceSum = 0;

        totalPrices.forEach(function (totalPrice) {
            const parsedPrice = parseFloat(totalPrice.innerText.replace('VND', ''));
            console.log('Parsed price:', parsedPrice);
            if (!isNaN(parsedPrice)) {
                totalPriceSum += parsedPrice;
            }
        });

        // Display the total sum
        const totalSumElement = document.getElementById('total_price_sum');
        totalSumElement.innerText = totalPriceSum.toFixed(2) + 'VND';

        // Parse the discount value
        const couponOptionValue = couponOption.value;
        const discountValueElement = document.getElementById('discount_value');
        discountValueElement.textContent = couponOptionValue + "%";
        const discountValueText = discountValueElement.innerText;
        const discountValue = parseFloat(discountValueText.substring(0, discountValueText.length - 1));

        // Calculate the final total
        const finalTotal = totalPriceSum - (totalPriceSum * discountValue / 100);
        const finalTotalElement = document.getElementById('total_price_final');
        finalTotalElement.innerText = finalTotal.toFixed(2) + 'VND';
    }
});

document.addEventListener('DOMContentLoaded', function () {
    const totalPrices = document.querySelectorAll('.total_price');
    const couponOption = document.querySelector('.coupon');
    let totalPriceSum = 0;

    totalPrices.forEach(function (totalPrice) {
        const parsedPrice = parseFloat(totalPrice.innerText.replace('$', ''));
        console.log('Parsed price:', parsedPrice);
        if (!isNaN(parsedPrice)) {
            totalPriceSum += parsedPrice;
        }
    });
    couponOption.addEventListener('change', function () {
        const couponOptionValue = couponOption.value;
        const discountValueElement = document.getElementById('discount_value');
        discountValueElement.textContent = couponOptionValue + "%";
    });

    // Display the total sum
    const totalSumElement = document.getElementById('total_price_sum');
    totalSumElement.innerText = totalPriceSum.toFixed(2) + 'VND';

    // Parse the discount value
    const couponOptionValue = couponOption.value;
    const discountValueElement = document.getElementById('discount_value');
    discountValueElement.textContent = couponOptionValue + "%";
    const discountValueText = discountValueElement.innerText;
    const discountValue = parseFloat(discountValueText.substring(0, discountValueText.length - 1));

    // Calculate the final total
    const finalTotal = totalPriceSum - (totalPriceSum * discountValue / 100);
    const finalTotalElement = document.getElementById('total_price_final');
    finalTotalElement.innerText = finalTotal.toFixed(2) + 'VND';
});

document.addEventListener('DOMContentLoaded', function () {
    const couponOption = document.querySelector('.coupon');
    const discountValueElement = document.getElementById('discount_value');
    const applyCouponButton = document.getElementById('btnCouponApply');

    applyCouponButton.addEventListener('click', function () {
        const selectedOption = couponOption.options[couponOption.selectedIndex];
        const discountPercentage = selectedOption.value;
        discountValueElement.textContent = discountPercentage + "%";
        updateFinalTotal();
    });

    function updateFinalTotal() {
        const totalSumElement = document.getElementById('total_price_sum');
        const totalPriceSum = parseFloat(totalSumElement.innerText.replace('VND', ''));

        const couponOptionValue = parseFloat(discountValueElement.textContent);
        const finalTotal = totalPriceSum - (totalPriceSum * couponOptionValue / 100);

        const finalTotalElement = document.getElementById('total_price_final');
        finalTotalElement.innerText = finalTotal.toFixed(2) + 'VND';
    }
});

document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('cartForm'); // Assuming your form has the ID 'cartForm'

    // Function to send data to servlet
    function sendDataToServlet(data) {
        // Create a new FormData object
        const formData = new FormData();

        // Append the data to FormData object
        for (const key in data) {
            formData.append(key, data[key]);
        }

        // Create a new XMLHttpRequest object
        const xhr = new XMLHttpRequest();

        // Configure the request
        xhr.open('POST', 'YourServletURL', true);

        // Set up the onload function
        xhr.onload = function () {
            // Check if the request was successful
            if (xhr.status >= 200 && xhr.status < 300) {
                // Request was successful, handle response if needed
                console.log('Data sent successfully!');
            } else {
                // Request failed
                console.error('Request failed with status:', xhr.status);
            }
        };

        // Send the request
        xhr.send(formData);
    }

    // Function to get values and send to servlet
    function sendValuesToServlet() {
        const totalSumElement = document.getElementById('total_price_sum');
        const discountValueElement = document.getElementById('discount_value');
        const finalTotalElement = document.getElementById('total_price_final');

        // Extract text content from elements
        const totalSum = totalSumElement.innerText.trim();
        const discountValue = discountValueElement.innerText.trim();
        const finalTotal = finalTotalElement.innerText.trim();

        // Prepare data to send
        const data = {
            totalSum: totalSum,
            discountValue: discountValue,
            finalTotal: finalTotal
        };

        // Send data to servlet
        sendDataToServlet(data);
    }

    // Attach event listener to the form submission
    form.addEventListener('submit', function (event) {
        // Prevent the default form submission
        event.preventDefault();

        // Send values to servlet
        sendValuesToServlet();
    });
});