<%-- 
    Document   : deleteDiscountModal
    Created on : Feb 25, 2024, 6:36:06 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="modal fade" id="deleteDiscountModal" tabindex="-1" role="dialog" aria-labelledby="delete-modal-label" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="delete-modal-label" style="color: black">Delete Form</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to delete this discount?</p>
            </div>
            <div class="modal-footer">
                <form action="../admin/dashboard?action=delete-discount" method="POST">
                    <div class="form-group" style="display: none">
                        <input type="text" class="form-control" id="idDiscountDeleteInput" name="discountId">
                    </div>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function deleteDiscountModal(id) {
        $('#idDiscountDeleteInput').val(id);
    }
    var msgdd = "${msgdd}";
    if (msgdd) {
        alert("Success: " + msgdd);
    }
    var errorcdd = "${errorcdd}";
    if (errorcdd) {
        alert("Error: " + errorcdd);
    }
</script>
