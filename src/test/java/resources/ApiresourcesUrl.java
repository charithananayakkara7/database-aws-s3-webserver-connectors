package resources;

public enum ApiresourcesUrl {

    AddplaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("/maps/api/place/delete/json"),
    CreateBookAPI("/sub/products"),
    GetBookAPI("/sub/products/");
    private String Urlresource;
    ApiresourcesUrl(String Urlresource) {
     this.Urlresource = Urlresource;
    }

    public String getresource(){

return Urlresource;

    }
}
