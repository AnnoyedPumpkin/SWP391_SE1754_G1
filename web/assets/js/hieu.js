
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