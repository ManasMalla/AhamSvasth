package com.manasmalla.ahamsvasth;

import java.util.HashMap;
import java.util.List;

public class DiseasesList {

    public static final Disease DIABETES = Disease.createDisease("Diabetes", R.drawable.ic_water);
    public static final Disease DIGESTION = Disease.createDisease("Improves Digestion", R.drawable.ic_water);
    public static final Disease BREATHING = Disease.createDisease("Improves Breathing", R.drawable.ic_water);
    public static final Disease OBESITY = Disease.createDisease("Weight Loss", R.drawable.ic_water);
    public static final Disease IMMUNITY = Disease.createDisease("Improves Immunity", R.drawable.ic_water);
    public static final Disease CORONAVIRUS = Disease.createDisease("Protects from Coronavirus", R.drawable.ic_water);
    public static final Disease MUSCLES = Disease.createDisease("Strengthens Muscles", R.drawable.ic_water);
    public static final Disease ANGER = Disease.createDisease("Stress", R.drawable.ic_water);
    public static final Disease CONSTIPATION = Disease.createDisease("Constipation", R.drawable.ic_water);
    public static final Disease ELASTIC = Disease.createDisease("Flexible", R.drawable.ic_water);
    public static final Disease THYROID = Disease.createDisease("Thyroid", R.drawable.ic_water);
    public static final Disease ASTHMA = Disease.createDisease("Asthma", R.drawable.ic_water);
    public static final Disease LOWERBACKPAIN = Disease.createDisease("Lower Back Pain", R.drawable.ic_water);
    public static final Disease DIARRHEA = Disease.createDisease("Diarrhea", R.drawable.ic_water);
    public static final Disease ARTHRITIS = Disease.createDisease("Arthritis", R.drawable.ic_water);

    public static final Disease COLD = Disease.createDisease("Cold", R.drawable.ic_water);

    public static final Disease SPINALCORD = Disease.createDisease("Spinal Cord Issues", R.drawable.ic_water);
    public static final Disease NECKINJURY = Disease.createDisease("Neck Injuries", R.drawable.ic_water);
    public static final Disease HIPINJURY = Disease.createDisease("Hip Injury", R.drawable.ic_water);
    public static final Disease INSOMNIA = Disease.createDisease("Insomnia", R.drawable.ic_water);
    public static final Disease KNEEPAIN = Disease.createDisease("Knee Pain", R.drawable.ic_water);
    public static final Disease MIGRANE = Disease.createDisease("Migrane", R.drawable.ic_water);
    public static final Disease HEADACHE = Disease.createDisease("Headache", R.drawable.ic_water);
    public static final Disease ULCER = Disease.createDisease("Ulcer", R.drawable.ic_water);
    public static final Disease HIGHBP = Disease.createDisease("High B.P.", R.drawable.ic_water);
    public static final Disease LOWBP = Disease.createDisease("Low B.P.", R.drawable.ic_water);
    public static final Disease PREGNANT = Disease.createDisease("Pregnant women", R.drawable.ic_water);

    public static HashMap<String, Integer> addDisease(Disease[] diseases) {
        HashMap<String, Integer> list = new HashMap<>();
        for (Disease disease : diseases) {
            list.put(disease.diseaseName, disease.diseaseIcon);
        }
        return list;
    }
    public static HashMap<String, Integer> addDonts(Disease[] diseases) {
        HashMap<String, Integer> list = new HashMap<>();
        for (Disease disease : diseases) {
            list.put(disease.diseaseName, disease.diseaseIcon);
        }
        return list;
    }
}

class Disease {
    String diseaseName;
    int diseaseIcon;

    public static Disease createDisease(String name, int drawable) {
        Disease disease = new Disease();
        disease.diseaseName = name;
        disease.diseaseIcon = drawable;
        return disease;
    }
}
