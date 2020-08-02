package model;


import com.google.firebase.Timestamp;

public class Journal {

    private String imageUrl;
    private String userId;
    private com.google.firebase.Timestamp timestamp;
    private String name;
    public Journal(){

    }

    public Journal(String imageUrl, String userId, Timestamp timeAdded, String name) {
        this.imageUrl = imageUrl;
        this.userId = userId;
        this.timestamp = timeAdded;
        this.name = name;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getTimeAdded() {
        return timestamp;
    }

    public void setTimeAdded(Timestamp timeAdded) {
        this.timestamp = timeAdded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
