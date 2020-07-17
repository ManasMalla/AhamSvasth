package com.manasmalla.ahamsvasth;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.manasmalla.ahamsvasth.DiseasesList.ANGER;
import static com.manasmalla.ahamsvasth.DiseasesList.ARTHRITIS;
import static com.manasmalla.ahamsvasth.DiseasesList.ASTHMA;
import static com.manasmalla.ahamsvasth.DiseasesList.COLD;
import static com.manasmalla.ahamsvasth.DiseasesList.CONSTIPATION;
import static com.manasmalla.ahamsvasth.DiseasesList.CORONAVIRUS;
import static com.manasmalla.ahamsvasth.DiseasesList.DIABETES;
import static com.manasmalla.ahamsvasth.DiseasesList.DIARRHEA;
import static com.manasmalla.ahamsvasth.DiseasesList.DIGESTION;
import static com.manasmalla.ahamsvasth.DiseasesList.ELASTIC;
import static com.manasmalla.ahamsvasth.DiseasesList.HEADACHE;
import static com.manasmalla.ahamsvasth.DiseasesList.HIGHBP;
import static com.manasmalla.ahamsvasth.DiseasesList.HIPINJURY;
import static com.manasmalla.ahamsvasth.DiseasesList.IMMUNITY;
import static com.manasmalla.ahamsvasth.DiseasesList.INSOMNIA;
import static com.manasmalla.ahamsvasth.DiseasesList.KNEEPAIN;
import static com.manasmalla.ahamsvasth.DiseasesList.LOWBP;
import static com.manasmalla.ahamsvasth.DiseasesList.LOWERBACKPAIN;
import static com.manasmalla.ahamsvasth.DiseasesList.MIGRANE;
import static com.manasmalla.ahamsvasth.DiseasesList.MUSCLES;
import static com.manasmalla.ahamsvasth.DiseasesList.NECKINJURY;
import static com.manasmalla.ahamsvasth.DiseasesList.OBESITY;
import static com.manasmalla.ahamsvasth.DiseasesList.PREGNANT;
import static com.manasmalla.ahamsvasth.DiseasesList.SPINALCORD;
import static com.manasmalla.ahamsvasth.DiseasesList.THYROID;
import static com.manasmalla.ahamsvasth.DiseasesList.ULCER;
import static com.manasmalla.ahamsvasth.DiseasesList.addDonts;

public class YogaPosesList {

    private static YogaPose mandukAasan, vajraAasan, dhanurAasan, shashakAasan, vakraAasan, ghomukhAasan, bhujangAasan, setuBadhanAasan, chakraAasan, halaAasan, phalakaasan, ushtraasan, salabhAasan, sarvangaAasan;
    private static YogaPose pavanaMukthaAasana, simhasana, naukasana, savaAasana, mayurAasan, bhadraAasan, utkaAasana, ardhaMatysendraAasana, ekaPadaRajakapotasana, uttanasana, trikonasana, balasana, kapalbharati, anulomVilom, om;
    public static YogaPose[] suryaNamaskaras;
    public static YogaPose donePose;

    public static List<YogaPose> createAasans() {
        om = YogaPose.createPose("Pranava Yoga", "Om ॐ Meditation", YogaPose.createListMethod(new String[]{"Sit on the ground straight.", "Move your right leg on your left thigh and your left leg on your right thigh.", "Move both your hands touching your knees and touch the 2 fingers", "Chant repeatedly Om ॐ while concentrating on your breathing."}), DiseasesList.addDisease(new Disease[]{IMMUNITY, CORONAVIRUS, COLD}), DiseasesList.addDonts(new Disease[]{}), R.drawable.yoga);
        mandukAasan = YogaPose.createPose("Mandukasana", "Frog Pose", YogaPose.createListMethod(new String[]{"Sit in Vajrasana", "Put your left palm on your navel area and the right palm over the left one", "Inhale deeply", "Exhale and bend forward and take suitable stretch on your naval region through your palms", "Hold the pose as long as you can with slow inhaling and exhaling", "Come to Vajrasana with deep inhale"}), DiseasesList.addDisease(new Disease[]{DiseasesList.DIABETES, DiseasesList.DIGESTION, DiseasesList.BREATHING, OBESITY, IMMUNITY}), DiseasesList.addDonts(new Disease[]{KNEEPAIN, MIGRANE, HEADACHE, HIGHBP, ULCER, PREGNANT}), R.drawable.mandukasana);
        vajraAasan = YogaPose.createPose("Vajrasana", "Thunderbolt Pose", YogaPose.createListMethod(new String[]{"Sit on the ground.", "Bend your legs and sit on your legs.", "Put both your hands on your knees.", "Maintain the position.", "Relax your body again."}), DiseasesList.addDisease(new Disease[]{DIGESTION, IMMUNITY, OBESITY, MUSCLES}), addDonts(new Disease[]{KNEEPAIN, ULCER, SPINALCORD, PREGNANT}), R.drawable.easy9_hero);
        dhanurAasan = YogaPose.createPose("Dhanurasana", "Bow Pose", YogaPose.createListMethod(new String[]{"Lay on the ground straight keep your face downwards.", "Move your both legs back slowly and hold them with your hands", "Maintain the position.", "Relax your body again"}), DiseasesList.addDisease(new Disease[]{MUSCLES, IMMUNITY, DIABETES}), addDonts(new Disease[]{HIGHBP, MIGRANE, INSOMNIA, NECKINJURY}), R.drawable.hard5_floor_bow);
        shashakAasan = YogaPose.createPose("Shashankasana", "Rabbit Pose", YogaPose.createListMethod(new String[]{"Lie on the ground with folded legs hands straight and heads down.", "Maintain the position.", "Relax your body again."}), DiseasesList.addDisease(new Disease[]{MUSCLES, ANGER, CONSTIPATION, DIABETES}), addDonts(new Disease[]{HIGHBP, KNEEPAIN, PREGNANT,}), R.drawable.easy8_baby);
        vakraAasan = YogaPose.createPose("Vakrasana", "Half Spinal Twist Pose", YogaPose.createListMethod(new String[]{"Sit with your leg both straight, and your hand touching your feet.", "Slowly move your right leg around your waist.", "With your right hand around your back hold the right leg fingers.", "Maintain the position.", "Do the same thing with your other leg and hands.", "Relax."}), DiseasesList.addDisease(new Disease[]{DIGESTION, CONSTIPATION, OBESITY, ELASTIC}), addDonts(new Disease[]{}), R.drawable.hard21_halffold_stretch);
        ghomukhAasan = YogaPose.createPose("Gomukhasana", "Cow Face Pose", YogaPose.createListMethod(new String[]{"Lie down on the floor with face down towards the ground.", "Now move your body up with bent knees and hand on ground and your face upwards.", "Maintain the position.", "Lay down and relax."}), DiseasesList.addDisease(new Disease[]{DIGESTION, CONSTIPATION, OBESITY, ELASTIC}), addDonts(new Disease[]{ULCER, SPINALCORD, HIPINJURY, PREGNANT}), R.drawable.a_6_cow);
        bhujangAasan = YogaPose.createPose("Bhujangasana", "Cobra Pose", YogaPose.createListMethod(new String[]{"Lie down on the floor with face down towards the ground.", "Slowly move your upper part of body upwards with the help of your hands.", "Maintain the position.", "Lay down and relax."}), DiseasesList.addDisease(new Disease[]{MUSCLES, ANGER, ASTHMA, OBESITY, DIGESTION, DIABETES, THYROID, HIGHBP}), addDonts(new Disease[]{LOWERBACKPAIN, HEADACHE, PREGNANT}), R.drawable.a_7_snake);
        setuBadhanAasan = YogaPose.createPose("Setu Bandhasana", "Bridge Pose", YogaPose.createListMethod(new String[]{"Lie down on the floor with face upwards and straight hands and legs.", "Bend your legs and pull your stomach upwards as much as you can.", "Bent your legs so that you can hold your legs with your hands or touch it.", "Maintain the position.", "Slowly relax your body on ground again."}), DiseasesList.addDisease(new Disease[]{MUSCLES, DIGESTION, ANGER, THYROID, HIGHBP, ASTHMA}), addDonts(new Disease[]{NECKINJURY}), R.drawable.hard17_bridge);
        chakraAasan = YogaPose.createPose("Chakrasana", "Wheel Pose", YogaPose.createListMethod(new String[]{"Lay on ground with upwards hand and straight legs.", "Slowly pull your body upwards with both your hands and legs on ground.", "Maintain the position.", "Relax your body again."}), DiseasesList.addDisease(new Disease[]{MUSCLES, THYROID, LOWERBACKPAIN, ASTHMA, ANGER}), addDonts(new Disease[]{SPINALCORD, HEADACHE, HIGHBP, HIPINJURY}), R.drawable.hard3_wheel);
        halaAasan = YogaPose.createPose("Halasana", "Plough Pose", YogaPose.createListMethod(new String[]{"Lay on the ground straight with straight hands and legs.", "Slowly move both your legs towards your head and touch your feet towards the grounds.", "Maintain the position.", "Relax."}), DiseasesList.addDisease(new Disease[]{ANGER, THYROID, ELASTIC, HEADACHE, DIABETES}), addDonts(new Disease[]{PREGNANT, ASTHMA, HIGHBP, NECKINJURY, DIARRHEA}), R.drawable.hard9_plow);
        phalakaasan = YogaPose.createPose("Phalakasana", "Plank Pose", YogaPose.createListMethod(new String[]{"Lie down on the floor with face down towards the ground.", "Slowly left your whole body up keeping your face looking towards the ground.", "Maintain the position.", "Slowly comeback to the ground and relax."}), DiseasesList.addDisease(new Disease[]{MUSCLES}), addDonts(new Disease[]{}), R.drawable.medium13_plank);
        ushtraasan = YogaPose.createPose("Ustrasana", "Camel Pose", YogaPose.createListMethod(new String[]{"Stand on your knees.", "Slowly hold your legs with your hands or touch.", "Maintain the position.", "Relax."}), DiseasesList.addDisease(new Disease[]{MUSCLES, LOWERBACKPAIN, DIGESTION, ASTHMA, THYROID, DIABETES, OBESITY, CONSTIPATION}), addDonts(new Disease[]{NECKINJURY, HIGHBP, INSOMNIA, MIGRANE}), R.drawable.medium8_camel);
        salabhAasan = YogaPose.createPose("Salabhasana", "Grasshopper Pose", YogaPose.createListMethod(new String[]{"Lay on the ground with your head down.", "Slowly move both your legs upward straight in air.", "Maintain the position.", "Relax."}), DiseasesList.addDisease(new Disease[]{MUSCLES, ANGER}), addDonts(new Disease[]{HEADACHE, LOWERBACKPAIN, NECKINJURY}), R.drawable.hard11_locust);
        sarvangaAasan = YogaPose.createPose("Sarvangasana", "Shoulder Stand Pose", YogaPose.createListMethod(new String[]{"Lay down on the ground with face upwards.", "Slowly move your whole body upwards with the help of your hands on the back.", "Maintain the position.", "Relax."}), DiseasesList.addDisease(new Disease[]{THYROID, ANGER, OBESITY, HEADACHE, IMMUNITY, ELASTIC, DIGESTION, DIABETES}), addDonts(new Disease[]{HIGHBP, PREGNANT, HEADACHE}), R.drawable.medium10_shoulder_stand);
        pavanaMukthaAasana = YogaPose.createPose("Pavanamuktasana", "Wind Relieving Pose", YogaPose.createListMethod(new String[]{"Lie flat on your back, keeping your legs and arms extended.", "Bring your knees together and hold with both your hands.", "Release left leg and keep it extended on the floor, hold the pose for 30 seconds.", "Now, bring back your left leg, hold both legs and release your right leg.", "Repeat for a minute, do thrice."}), DiseasesList.addDisease(new Disease[]{MUSCLES, CONSTIPATION, DIARRHEA, DIGESTION, LOWERBACKPAIN, ELASTIC, OBESITY}), addDonts(new Disease[]{PREGNANT, HIGHBP, NECKINJURY}), R.drawable.pavanamuktasana);
        savaAasana = YogaPose.createPose("Shavasana", "Corpse Pose", YogaPose.createListMethod(new String[]{"Lie down on the ground straight and relax."}), DiseasesList.addDisease(new Disease[]{ANGER, HEADACHE, INSOMNIA, HIGHBP, DIABETES, THYROID}), addDonts(new Disease[]{LOWERBACKPAIN, PREGNANT}), R.drawable.easy11_corpse);
        mayurAasan = YogaPose.createPose("Mayurasana", "Peacock Pose", YogaPose.createListMethod(new String[]{"Sit on the ground.", "Slowly put both your hands-on ground and lift your body in air.", "Maintain the position.", "Relax."}), DiseasesList.addDisease(new Disease[]{MUSCLES}), addDonts(new Disease[]{}), R.drawable.hard10_crow);
        bhadraAasan = YogaPose.createPose("Bhadrasana", "Butterfly Pose", YogaPose.createListMethod(new String[]{"Sit on the ground with your both feet touching each other.", "Put both your hands towards your legs.", "Maintain the position.", "Slowly relax and sit normal."}), DiseasesList.addDisease(new Disease[]{MUSCLES, ANGER, SPINALCORD}), addDonts(new Disease[]{ARTHRITIS}), R.drawable.a_9_butterfly);
        utkaAasana = YogaPose.createPose("Utkatasana", "Chair Pose", YogaPose.createListMethod(new String[]{"Stand on the ground straight.", "Now bent and keep your hands upwards.", "Maintain the position.", "Relax your body again."}), DiseasesList.addDisease(new Disease[]{MUSCLES}), addDonts(new Disease[]{HEADACHE, INSOMNIA, LOWBP}), R.drawable.hard4_chair);
        ardhaMatysendraAasana = YogaPose.createPose("Ardha Matsyendrāsana", "Lord of the Fishes Pose", YogaPose.createListMethod(new String[]{"Sit on the ground.", "Bend your left leg towards your chest and take your right hand around the left leg and hold it with other hand.", "Turn your head on the left side.", "Maintain the position.", "Bend your right leg towards your chest and take your left hand around the right leg and hold it with other hand.", "Turn your head on the right side.", "Maintain the position.", "Relax."}), DiseasesList.addDisease(new Disease[]{MUSCLES, DIGESTION, ASTHMA, IMMUNITY, DIABETES}), addDonts(new Disease[]{SPINALCORD}), R.drawable.hard7_half_spinal_twist);
        ekaPadaRajakapotasana = YogaPose.createPose("Eka Pāda Rājakapotāsana", "One Legged King Pigeon Pose", YogaPose.createListMethod(new String[]{"Slight the right knee forward between the hands.", "Slide the right foot over to the left.", "Then, lowering the hips towards the floor, slide your left leg back and stretch it.", "Press down the palms or the fingertips on the floor, then reach the crown of the head up.", "Repeat the same with your left knee this time."}), DiseasesList.addDisease(new Disease[]{MUSCLES}), addDonts(new Disease[]{KNEEPAIN, HIPINJURY}), R.drawable.ekapadakapotasana);
        uttanasana = YogaPose.createPose("Uttanasana", "Standing Forward Bend Pose", YogaPose.createListMethod(new String[]{"Stand straight.", "Slowly move the upper body down touching your feet with your hands.", "Maintain the position.", "Relax."}), DiseasesList.addDisease(new Disease[]{ANGER, MUSCLES, DIGESTION, HEADACHE, INSOMNIA, ASTHMA, HIGHBP}), addDonts(new Disease[]{LOWERBACKPAIN}), R.drawable.medium9_standing_forward_bend);
        trikonasana = YogaPose.createPose("Trikonasana", "Extended Triangle Pose", YogaPose.createListMethod(new String[]{"Stand straight with wide spread.", "Slowly touch your right hand on ground bending right side.", "Slowly touch your left hand on ground bending left side.", "Relax."}), DiseasesList.addDisease(new Disease[]{MUSCLES, ANGER, DIGESTION, LOWERBACKPAIN, PREGNANT,}), addDonts(new Disease[]{DIARRHEA, HEADACHE, LOWBP, HIGHBP, NECKINJURY}), R.drawable.medium2_triangle);
        kapalbharati = YogaPose.createPose("Kapalabhati", "Breath of Fire Pose", YogaPose.createListMethod(new String[]{"Sit comfortably in Padmasana. Take a deep breath in.", "As you exhale, pull your stomach.", "Pull your navel in back towards the spine as much as you comfortably can while keeping your right hand on the stomach to feel the abdominal muscles contract.", "Again take a deep breath in."}), DiseasesList.addDisease(new Disease[]{IMMUNITY, CORONAVIRUS, COLD}), addDonts(new Disease[]{}), R.drawable.kapalbhati);
        anulomVilom = YogaPose.createPose("Pranayama", "Alternate Breathing Pose", YogaPose.createListMethod(new String[]{"Sit in Padmasana.", "If you begin from the left then close the right nostril with your right thumb and inhale slowly to fill up your lungs.", "Now, exhale slowly from the right nostril.", "Now, do the reverse and so a few times."}), DiseasesList.addDisease(new Disease[]{IMMUNITY, CORONAVIRUS, COLD}), addDonts(new Disease[]{}), R.drawable.anulomvilom);
        simhasana = YogaPose.createPose("Simhasana", "Lion Pose", YogaPose.createListMethod(new String[]{"Sit on the ground on your knees and hands tough your legs.", "Keep your tongue out and maintain the position.", "Relax."}), DiseasesList.addDisease(new Disease[]{ANGER, MUSCLES, THYROID}), DiseasesList.addDonts(new Disease[]{KNEEPAIN}), R.drawable.a_8_lion);
        naukasana = YogaPose.createPose("Naukasana", "Boat Pose", YogaPose.createListMethod(new String[]{"Sit on the ground with straight legs.", "Hold your legs with your hands and move your body back.", "Maintain the position.", "Relax your body again."}), DiseasesList.addDisease(new Disease[]{THYROID, ANGER, DIGESTION, DIABETES}), DiseasesList.addDonts(new Disease[]{ASTHMA, DIARRHEA, HEADACHE, INSOMNIA, LOWBP, PREGNANT, NECKINJURY}), R.drawable.hard2_full_boat);
        suryaNamaskaras = new YogaPose[]{
                YogaPose.createPose("Surya Namaskaras", "Sun Salutations", YogaPose.createListMethod(new String[]{"It is recommended to perform Surya Namaskar in the early morning during the sunrise, in an open ground as it will benefit you a lot."}), DiseasesList.addDisease(new Disease[]{IMMUNITY, CORONAVIRUS, COLD, MUSCLES, INSOMNIA, ANGER, ELASTIC, OBESITY, HIGHBP, LOWBP, LOWERBACKPAIN}), DiseasesList.addDonts(new Disease[]{}), R.drawable.yoga),
                YogaPose.createPose("Pranamasana","The Prayer pose",YogaPose.createListMethod(new String[]{"Join your hands together (in namaskar pose) touching your chest.", "Relaxing the whole body you have to take a deep breath and exhale, focusing on your breathing."}),DiseasesList.addDisease(new Disease[]{ANGER}), DiseasesList.addDonts(new Disease[]{}), R.drawable.pranamasana),
                YogaPose.createPose("Hastottanasana","The Raised Arms pose",YogaPose.createListMethod(new String[]{"Touching the biceps to your ears, raise your hands up and bend backward as much as possible."}),DiseasesList.addDisease(new Disease[]{ELASTIC,LOWERBACKPAIN,SPINALCORD}), DiseasesList.addDonts(new Disease[]{}), R.drawable.hastottanasana),
                YogaPose.createPose("Hasta Padasana","The Standing Forward Bend",YogaPose.createListMethod(new String[]{"Try to touch the ground bending your back and keeping your legs straight."}),DiseasesList.addDisease(new Disease[]{MUSCLES,DIGESTION}), DiseasesList.addDonts(new Disease[]{}), R.drawable.hasta_padasana),
                YogaPose.createPose("Ashwa Sanchalanasana","The Equestrian pose",YogaPose.createListMethod(new String[]{"While inhaling, extend your left leg behind as far as possible.", "Now, fold your right knee and place it close towards the right part of the chest.", "Keep your hands on the floor.", "Looking up, bend your neck slightly backward."}),DiseasesList.addDisease(new Disease[]{ELASTIC}), DiseasesList.addDonts(new Disease[]{}), R.drawable.ashwa_sanchalanasana),
                YogaPose.createPose("Dandasana","The Staff pose",YogaPose.createListMethod(new String[]{"Stretching your legs behind, bring your body into a straight line.", "Keeping it parallel to the floor put all your weight on your arms and feet."}),DiseasesList.addDisease(new Disease[]{MUSCLES}), DiseasesList.addDonts(new Disease[]{}), R.drawable.dandasana),
                YogaPose.createPose("Ashtanga Namaskar","The Eight Limbed Salute Pose",YogaPose.createListMethod(new String[]{"Laying down on your abdominal part rest your chin, chest, hands, feet on the ground and raise your hips and abdomen high."}),DiseasesList.addDisease(new Disease[]{MUSCLES}), DiseasesList.addDonts(new Disease[]{}), R.drawable.ashtanga_namaskar),
                YogaPose.createPose("Bhujangasana", "The Cobra Pose", YogaPose.createListMethod(new String[]{"Lie down on the floor with face down towards the ground.", "Slowly move your upper part of body upwards with the help of your hands.", "Maintain the position.", "Lay down and relax."}), DiseasesList.addDisease(new Disease[]{MUSCLES, ANGER, ASTHMA, OBESITY, DIGESTION, DIABETES, THYROID, HIGHBP,HEADACHE, LOWERBACKPAIN}), addDonts(new Disease[]{LOWERBACKPAIN, HEADACHE, PREGNANT}), R.drawable.bhujangasana),
                YogaPose.createPose("Adho Mukha Svanasana","The Downward Dog Pose",YogaPose.createListMethod(new String[]{"Elevate your hips up keeping your hands and feet on the ground."}),DiseasesList.addDisease(new Disease[]{ANGER,HIGHBP,LOWBP,COLD,CORONAVIRUS}), DiseasesList.addDonts(new Disease[]{}), R.drawable.adho_mukha_svanasana),
                YogaPose.createPose("Adho Mukha Svanasana","The Downward Dog Pose",YogaPose.createListMethod(new String[]{"Elevate your hips up keeping your hands and feet on the ground."}),DiseasesList.addDisease(new Disease[]{ANGER,HIGHBP,LOWBP,COLD,CORONAVIRUS}), DiseasesList.addDonts(new Disease[]{}), R.drawable.adho_mukha_svanasana),
                YogaPose.createPose("Bhujangasana", "The Cobra Pose", YogaPose.createListMethod(new String[]{"Lie down on the floor with face down towards the ground.", "Slowly move your upper part of body upwards with the help of your hands.", "Maintain the position.", "Lay down and relax."}), DiseasesList.addDisease(new Disease[]{MUSCLES, ANGER, ASTHMA, OBESITY, DIGESTION, DIABETES, THYROID, HIGHBP,HEADACHE, LOWERBACKPAIN}), addDonts(new Disease[]{LOWERBACKPAIN, HEADACHE, PREGNANT}), R.drawable.bhujangasana),
                YogaPose.createPose("Ashtanga Namaskar","The Eight Limbed Salute Pose",YogaPose.createListMethod(new String[]{"Laying down on your abdominal part rest your chin, chest, hands, feet on the ground and raise your hips and abdomen high."}),DiseasesList.addDisease(new Disease[]{MUSCLES}), DiseasesList.addDonts(new Disease[]{}), R.drawable.ashtanga_namaskar),
                YogaPose.createPose("Dandasana","The Staff pose",YogaPose.createListMethod(new String[]{"Stretching your legs behind, bring your body into a straight line.", "Keeping it parallel to the floor put all your weight on your arms and feet."}),DiseasesList.addDisease(new Disease[]{MUSCLES}), DiseasesList.addDonts(new Disease[]{}), R.drawable.dandasana),
                YogaPose.createPose("Ashwa Sanchalanasana","The Equestrian pose",YogaPose.createListMethod(new String[]{"While inhaling, extend your left leg behind as far as possible.", "Now, fold your right knee and place it close towards the right part of the chest.", "Keep your hands on the floor.", "Looking up, bend your neck slightly backward."}),DiseasesList.addDisease(new Disease[]{ELASTIC}), DiseasesList.addDonts(new Disease[]{}), R.drawable.ashwa_sanchalanasana),
                YogaPose.createPose("Hasta Padasana","The Standing Forward Bend",YogaPose.createListMethod(new String[]{"Try to touch the ground bending your back and keeping your legs straight."}),DiseasesList.addDisease(new Disease[]{MUSCLES,DIGESTION}), DiseasesList.addDonts(new Disease[]{}), R.drawable.hasta_padasana),
                YogaPose.createPose("Hastottanasana","The Raised Arms pose",YogaPose.createListMethod(new String[]{"Touching the biceps to your ears, raise your hands up and bend backward as much as possible."}),DiseasesList.addDisease(new Disease[]{ELASTIC,LOWERBACKPAIN,SPINALCORD}), DiseasesList.addDonts(new Disease[]{}), R.drawable.hastottanasana),
                YogaPose.createPose("Pranamasana","The Prayer pose",YogaPose.createListMethod(new String[]{"Join your hands together (in namaskar pose) touching your chest.", "Relaxing the whole body you have to take a deep breath and exhale, focusing on your breathing."}),DiseasesList.addDisease(new Disease[]{ANGER}), DiseasesList.addDonts(new Disease[]{}), R.drawable.pranamasana),
        };
        donePose = YogaPose.createPose("Great Job!", "Well Done!", YogaPose.createListMethod(new String[]{"You completed today's workout!\uD83C\uDFC5","Let's continue again tommorow!"}), DiseasesList.addDisease(new Disease[]{}), DiseasesList.addDonts(new Disease[]{}), R.drawable.you_did_it);

        List<YogaPose> aasans = new ArrayList<>();
        aasans.add(om);
        aasans.add(anulomVilom);
        aasans.add(kapalbharati);
        Log.i("SN", Arrays.asList(suryaNamaskaras).toString());
        aasans.addAll(Arrays.asList(suryaNamaskaras));
        aasans.add(vajraAasan);
        aasans.add(mandukAasan);
        aasans.add(dhanurAasan);
        aasans.add(shashakAasan);
        aasans.add(vakraAasan);
        aasans.add(ghomukhAasan);
        aasans.add(bhujangAasan);
        aasans.add(setuBadhanAasan);
        aasans.add(chakraAasan);
        aasans.add(halaAasan);
        aasans.add(phalakaasan);
        aasans.add(ushtraasan);
        aasans.add(salabhAasan);
        aasans.add(sarvangaAasan);
        aasans.add(pavanaMukthaAasana);
        aasans.add(simhasana);
        aasans.add(naukasana);
        aasans.add(savaAasana);
        aasans.add(mayurAasan);
        aasans.add(bhadraAasan);
        aasans.add(utkaAasana);
        aasans.add(ardhaMatysendraAasana);
        aasans.add(ekaPadaRajakapotasana);
        aasans.add(uttanasana);
        aasans.add(trikonasana);
        return aasans;
    }

}
