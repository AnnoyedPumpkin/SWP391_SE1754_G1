<%-- 
    Document   : addProductModal
    Created on : Feb 17, 2024, 12:35:31 PM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Modal -->
<div class="modal fade" id="addProductModal" tabindex="-1" role="dialog" aria-labelledby="addProductModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addProductModalLabel">Add Product Form</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="addProductForm" action="manageproduct?action=add-product" method="POST" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="pname">Product Name: </label>
                        <input type="text" class="form-control" id="productnameInput" name="product_name">
                        <div id="productnameError" class="error"></div>
                    </div>
                    <div class="form-group">
                        <label for="productDescription">Product Description: </label>
                        <input type="text" class="form-control" id="productdescriptionInput" name="productDescription">
                        <div id="productdescriptionError" class="error"></div>
                    </div>
                    <div class="form-group">
                        <label for="productPrice">Product Price: </label>
                        <input type="text" class="form-control" id="productpriceInput" name="productPrice">
                        <div id="productpriceError" class="error"></div>
                    </div>
                    <div class="form-group">
                        <label for="stock">Stock: </label>
                        <input type="text" class="form-control" id="stockInput" name="stock">
                        <div id="stockError" class="error"></div>
                    </div>
                    <div class="form-group">
                        <label for="colorSelect">Color:</label>
                        <select class="form-control" id="colorSelect" name="color" required>
                            <<option value="">Select color</option>
                            <c:forEach items="${listC}" var="c">
                                <option value="${c.id}">${c.color}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="cateSelect">Category: </label>
                        <select class="form-control" id="cateSelect" name="cate" required>
                            <<option value="">Select category</option>
                            <c:forEach items="${listCate}" var="cate">
                                <option value="${cate.id}">${cate.category}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="sizeSelect">Size: </label>
                        <select class="form-control" id="sizeSelect" name="size" required>
                            <<option value="">Select size</option>
                            <c:forEach items="${listS}" var="s">
                                <option value="${s.id}">${s.size}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="brandSelect">Brand: </label>
                        <select class="form-control" id="brandSelect" name="brand" required>
                            <<option value="">Select brand</option>
                            <c:forEach items="${listB}" var="b">
                                <option value="${b.id}">${b.brand}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="genderSelect">Gender: </label>
                        <select class="form-control" id="genderSelect" name="gender" required>
                            <<option value="">Select gender</option>
                            <c:forEach items="${listG}" var="g">
                                <option value="${g.id}">${g.gender}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="image">Product Image Layout: </label>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Upload (*.png, *.jpg, *.jpeg)</span>
                            </div>
                            <div class="custom-file">
                                <input accept="image/png, image/jpg, image/jpeg" type="file" class="custom-file-input" id="image" name="image" onchange="displayImage(this)" required>
                                <label class="custom-file-label">Choose file</label>
                            </div>
                        </div>
                        <img id="previewImage" src="#" alt="Preview" style="display: none; max-width: 300px; max-height: 300px;">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                <button type="submit" class="btn btn-primary" form="addProductForm" onclick="validateForm()">Add</button>
            </div>
        </div>
    </div>
</div>

<script>

    var errorap = "${errorap}";
    if (errorap) {
        alert("Error: " + errorap);
    }

    var msgap = "${msgap}";
    if (msgap) {
        alert("Success: " + msgap);
    }

    function validateForm() {
        

        let productName = $('#productnameInput').val();
        let productDescription = $('#productdescriptionInput').val();
        let productPrice = $('#productpriceInput').val();
        let stock = $('#stockInput').val();
        // Remove existing error messages
        $('.error').html('');

        if (productName === '') {
            $('#productnameError').html('Product Name cannot be blank');
        } else if (productName.value.length < 3) {
            $('#productnameError').html('Product Name must be at least 3 characters long.');
        } else if (!/^[A-Za-z\s\u0080-\uFFFF]+$/.test(productName.value)) {
            $('#productnameError').html('Product Name must contain only letters');
        }

        if (productDescription === '') {
            $('#productdescriptionError').html('Product Description cannot be blank');
        } else if (productDescription.value.length < 10) {
            $('#productdescriptionError').html('Product Description must be at least 10 characters long.');
        } else if (!/^[A-Za-z0-9\s\u0080-\uFFFF]+$/.test(productDescription.value)) {
            $('#productdescriptionError').html('Product Description must contain only letters and numbers');
        }

        if (productPrice === '') {
            $('#productpriceError').html('Product Price cannot be blank');
        } else if (!/^[0-9]+$/.test(productPrice.value) || parseFloat(productPrice) < 0) {
            $('#productpriceError').html('Product Price must contain only numbers and larger than zero');
        }
        
        if (stock === '') {
            $('#stockError').html('Stock cannot be blank');
        } else if (!/^[0-9]+$/.test(stock.value) || parseInt(stock) < 0) {
            $('#stockError').html('Stock must contain only numbers');
        }

        // Check if there are any error messages
        let error = '';
        $('.error').each(function () {
            error += $(this).html();
        });
        if (error === '') {
            $('#addProductForm').submit();
        } else {
            event.preventDefault();
        }
    }


    function displayImage(input) {
        var previewImage = document.getElementById("previewImage");
        var file = input.files[0];
        var reader = new FileReader();

        reader.onload = function (e) {
            previewImage.src = e.target.result;
            previewImage.style.display = "block";
        };
        reader.readAsDataURL(file);
    }
</script>
