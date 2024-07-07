package resources;

public enum ApiUrlHelper {

    AddplaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("/maps/api/place/delete/json"),
    CreateBookAPI("/sub/products"),
    GetBookAPI("/sub/products/");
    private String Urlresource;
    ApiUrlHelper(String Urlresource) {
     this.Urlresource = Urlresource;
    }

    public String getresource(){

return Urlresource;

    }
}
