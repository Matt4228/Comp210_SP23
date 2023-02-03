package assn02;

public class ProductBatch{
    //input class fields
    private String _date;
    private String _time;
    private String _type;
    private double _fee;
    private int _quant;
    private double _duration;
    private double _cost;

    //factory finals
    private final String[] CATEGORIES = {"phone", "laptop", "smart_watch"};
    private final int WAGE = 16;

    //per unit cost --> variable to sort the list over
    private double UnitCost;

    //default constructor
    public ProductBatch() {
        _date = null;
        _time = null;
        _type = null;
        _fee = -1;
        _quant = -1;
        _duration = -1;
        _cost = -1;
    }

    //constructor
    public ProductBatch(String date, String time, String type,
                        double fee, int quant, double duration,
                        double cost) {
        _date = date;
        _time = time;
        _type = type;
        _fee = fee;
        _quant = quant;
        _duration = duration;
        _cost = cost;
    }

    public void setDate(String date) {
        _date = date;
    }

    public void setTime(String time) {
        _time = time;
    }

    public void setType(String type) {
         _type = type;
    }

    public void setFee(double fee) {
        _fee = fee;
    }

    public void setQuant(int quant) {
        _quant = quant;
    }

    public void setDuration(double duration) {
        _duration = duration;
    }

    public void setCost(double cost) {
        _cost = cost;
    }

    public String getDate() {
        return _date;
    }

    //get method for time
    public String getTime() {
        return _time;
    }

    public String getType() {
        return _type;
    }
    public double getFee() {
        return _fee;
    }

    public int getQuant() {
        return _quant;
    }

    public double getDuration() {
        return _duration;
    }

    public double getCost() {
        return _cost;
    }

    //returns batch data in the format it was given
    public String printBatch() {
        return _date + " " + _time + " " + _type + " " + _fee + " " + _quant + " " + _duration + " " + _cost;
    }




}
