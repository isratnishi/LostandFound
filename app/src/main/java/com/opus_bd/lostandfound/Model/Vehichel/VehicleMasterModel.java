
package com.opus_bd.lostandfound.Model.Vehichel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleMasterModel {

    @SerializedName("vehicleTypes")
    @Expose
    private List<VehicleType> vehicleTypes ;
    @SerializedName("vehicleModels")
    @Expose
    private List<VehicleModel> vehicleModels ;
    @SerializedName("registrationLevels")
    @Expose
    private List<RegistrationLevel> registrationLevels ;
    @SerializedName("metropolitanAreas")
    @Expose
    private List<MetropolitanArea> metropolitanAreas ;
    @SerializedName("districts")
    @Expose
    private List<District> districts ;
    @SerializedName("thanas")
    @Expose
    private List<Thana> thanas ;
    @SerializedName("colors")
    @Expose
    private List<Color> colors ;

    public List<VehicleType> getVehicleTypes() {
        return vehicleTypes;
    }

    public void setVehicleTypes(List<VehicleType> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }

    public List<VehicleModel> getVehicleModels() {
        return vehicleModels;
    }

    public void setVehicleModels(List<VehicleModel> vehicleModels) {
        this.vehicleModels = vehicleModels;
    }

    public List<RegistrationLevel> getRegistrationLevels() {
        return registrationLevels;
    }

    public void setRegistrationLevels(List<RegistrationLevel> registrationLevels) {
        this.registrationLevels = registrationLevels;
    }

    public List<MetropolitanArea> getMetropolitanAreas() {
        return metropolitanAreas;
    }

    public void setMetropolitanAreas(List<MetropolitanArea> metropolitanAreas) {
        this.metropolitanAreas = metropolitanAreas;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public List<Thana> getThanas() {
        return thanas;
    }

    public void setThanas(List<Thana> thanas) {
        this.thanas = thanas;
    }

    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

}
