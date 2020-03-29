package chapter.android.aweme.ss.com.homework;

public class User {

    private String title;
    private String description;
    private String time;
    private int imageId;

    public User(String title, String description, String time,int imageId){
        this.title = title;
        this.description = description;
        this.time = time;
        this.imageId = imageId;

    }

    public String getTitle() {
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getTime(){
        return time;
    }

    public int getImageId() {
        return imageId;
    }
}