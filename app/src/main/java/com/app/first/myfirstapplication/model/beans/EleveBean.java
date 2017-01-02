package com.app.first.myfirstapplication.model.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Random;

public class EleveBean implements Parcelable {
    private String nom;
    private String prenom;
    private String photo;
    private ArrayList<String> imageInternet = new ArrayList<>();

    public EleveBean() {
        imageInternet.add("https://pixabay.com/static/uploads/photo/2014/04/02/17/02/girl-307747_960_720.png");
        imageInternet.add("http://www.coloriage.tv/dessincolo/ecolier.png");
        imageInternet.add("http://clamart-lafontaine-blog.e-monsite.com/medias/images/eleve.png");
        imageInternet.add("http://ekladata.com/2NvmX2GdczA71ZMewxFwyR9CesE@350x586.png");
        photo = imageInternet.get(new Random().nextInt(4));
    }

    public EleveBean(String nom, String prenom) {
        this();
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhoto() {
        return photo;
    }

    protected EleveBean(Parcel in) {
        nom = in.readString();
        prenom = in.readString();
        photo = in.readString();
        if (in.readByte() == 0x01) {
            imageInternet = new ArrayList<String>();
            in.readList(imageInternet, String.class.getClassLoader());
        }
        else {
            imageInternet = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeString(photo);
        if (imageInternet == null) {
            dest.writeByte((byte) (0x00));
        }
        else {
            dest.writeByte((byte) (0x01));
            dest.writeList(imageInternet);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<EleveBean> CREATOR = new Parcelable.Creator<EleveBean>() {
        @Override
        public EleveBean createFromParcel(Parcel in) {
            return new EleveBean(in);
        }

        @Override
        public EleveBean[] newArray(int size) {
            return new EleveBean[size];
        }
    };
}