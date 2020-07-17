package com.manasmalla.ahamsvasth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class YogaPose {
    private String poseName;
    private List<String> poseMethod;
    private HashMap<String, Integer> poseCures, poseDonotDo;
    private int posePicture;
    private String angloName;

    public void setPoseName(String poseName) {
        this.poseName = poseName;
    }

    public void setPoseMethod(List<String> poseMethod) {
        this.poseMethod = poseMethod;
    }

    public void setPoseCures(HashMap<String, Integer> poseCures) {
        this.poseCures = poseCures;
    }

    public void setPosePicture(int posePicture) {
        this.posePicture = posePicture;
    }
    public void setAngloName(String angloName) {
        this.angloName = angloName;
    }

    public void setPoseDonotDo(HashMap<String, Integer> poseDonotDo) {
        this.poseDonotDo = poseDonotDo;
    }

    public List<String> getDescription() {
        return poseMethod;
    }

    public String getName() {
        return poseName;
    }

    public String getAngloName() {
        return angloName;
    }

    public HashMap<String, Integer> getCures() {
        return poseCures;
    }

    public HashMap<String, Integer> getDonotDo() {
        return poseDonotDo;
    }

    public int getPicture() {
        return posePicture;
    }

    public static YogaPose createPose(String yPoseName, String yAngloName, List<String> yPoseMethod, HashMap<String, Integer> yPoseCures, HashMap<String, Integer> yPoseDontDo, int yPosePicture){
        YogaPose yogaPose = new YogaPose();
        yogaPose.setPoseName(yPoseName);
        yogaPose.setAngloName(yAngloName);
        yogaPose.setPoseMethod(yPoseMethod);
        yogaPose.setPoseCures(yPoseCures);
        yogaPose.setPoseDonotDo(yPoseDontDo);
        yogaPose.setPosePicture(yPosePicture);
        return yogaPose;
    }

    public static List<String> createListMethod(String[] methodArray){
        List<String> array = new ArrayList<>();
        array.addAll(Arrays.asList(methodArray));
        return array;
    }
}
