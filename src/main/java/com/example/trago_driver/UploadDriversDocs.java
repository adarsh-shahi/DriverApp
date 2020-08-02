package com.example.trago_driver;

public class UploadDriversDocs {

    private String mName;
    private String mImageUrl;

    public UploadDriversDocs(){




    }

    public UploadDriversDocs(String name,String imageUrl){
        mName=name;
        mImageUrl=imageUrl;

    }


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }
}
