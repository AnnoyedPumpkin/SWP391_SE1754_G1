<%-- 
    Document   : deleteProductModal
    Created on : Feb 19, 2024, 2:57:19 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="modal fade" id="deleteProductModal" tabindex="-1" role="dialog" aria-labelledby="delete-modal-label" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="delete-modal-label" style="color: white">Delete Form</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to delete this product?</p>
            </div>
            <div class="modal-footer">
                <form action="manageproduct?action=delete-product" method="POST">
                    <div class="form-group" style="display: none">
                        <input type="text" class="form-control" id="idProductDeleteInput" name="product_ID">
                    </div>
                    <div class="form-group" style="display: none">
                        <input type="text" class="form-control" id="idColorDeleteInput" name="color_ID">
                    </div>
                    <div class="form-group" style="display: none">
                        <input type="text" class="form-control" id="idCategoryDeleteInput" name="category_ID">
                    </div>
                    <div class="form-group" style="display: none">
                        <input type="text" class="form-control" id="idSizeDeleteInput" name="size_ID">
                    </div>
                    <div class="form-group" style="display: none">
                        <input type="text" class="form-control" id="idBrandDeleteInput" name="brand_ID">
                    </div>
                    <div class="form-group" style="display: none">
                        <input type="text" class="form-control" id="idGenderDeleteInput" name="gender_ID">
                    </div>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function deleteProductModal(id, colorID, categoryID, sizeID, brandID, genderID, ) {
        $('#idProductDeleteInput').val(id);
        $('#idColorDeleteInput').val(colorID);
        $('#idCategoryDeleteInput').val(categoryID);
        $('#idSizeDeleteInput').val(sizeID);
        $('#idBrandDeleteInput').val(brandID);
        $('#idGenderDeleteInput').val(genderID);
    }
    var msgdp = "${msgdp}";
    if (msgdp) {
        alert("Success: " + msgdp);
    }
    
    var errordp = "${errdp}";
    if (errordp) {
        alert("Error: " + errordp);
    }
</script>


