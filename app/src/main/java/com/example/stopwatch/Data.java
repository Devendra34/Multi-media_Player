package com.example.stopwatch;


public class Data implements Comparable<Data>{
    private String title;
    private String path;
    private Boolean isMusic;


    public Data(String title, String path,Boolean isMusic) {
        this.title = title;
        this.path = path;
        this.isMusic = isMusic;
    }


    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }

    @Override
    public int compareTo(Data data) {
        if(this.isMusic){
            if(this.title.compareTo(data.title) > 0){
                return 1;
            }else
                return -1;
        } else {
            String folder1,folder2;
            String dir1[] = this.path.split("/");
            if(dir1.length>=2)
                folder1 = dir1[dir1.length-2];
            else
                folder1 = dir1[dir1.length-1];
            folder1 = folder1.toLowerCase();

            String dir2[] = data.path.split("/");
            if(dir1.length>=2)
                folder2 = dir1[dir1.length-2];
            else
                folder2 = dir1[dir1.length-1];
            folder2 = folder2.toLowerCase();

            if(folder1.compareTo(folder2) > 0){
                return 1;
            }else if(folder1.compareTo(folder2) < 0)
                return -1;
            else{
                return 0;
            }
        }
    }

}
