public class Number {
    private String style = "-fx-text-fill:";
    private int count = 0;

    public String getStyle() {
        return style;
    }

    Number(int count){
        this.count = count;
        switch (count){
            case 1:
                style += "blue;";
                break;
            case 2:
                style+= "green;";
                break;
            case 3:
                style += "red;";
                break;
            case 4:
                style += "purple;";
                break;
            case 5:
                style += "maroon;";
                break;
            case 6:
                style += "turquoise;";
                break;
            case 7:
                style += "black;";
                break;
            case 8:
                style += "grey;";
                break;
        }
    }
    String getCount(){
        return Integer.toString(count);
    }
}
