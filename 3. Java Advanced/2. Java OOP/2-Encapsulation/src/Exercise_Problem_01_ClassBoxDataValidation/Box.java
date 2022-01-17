package Exercise_Problem_01_ClassBoxDataValidation;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        if (!isValid(length)) {
            throw new IllegalArgumentException("Length cannot be zero or negative.");
        }

        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (!isValid(width)) {
            throw new IllegalArgumentException("Width cannot be zero or negative.");
        }

        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (!isValid(height)) {
            throw new IllegalArgumentException("Height cannot be zero or negative.");
        }
        this.height = height;
    }

    private boolean isValid(double parameter) {
        return parameter > 0;
    }

    public double calculateSurfaceArea() {
        return 2 * this.getLength() * this.getWidth() + 2 * this.getLength() * this.getHeight() + 2 * this.getWidth() * this.getHeight();
    }

    public double calculateLateralSurfaceArea() {
        return 2 * this.getLength() * this.getHeight() + 2 * this.getWidth() * this.getHeight();
    }

    public double calculateVolume() {
        return this.getLength() * this.getHeight() * this.getWidth();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("Surface Area - %.2f",
                this.calculateSurfaceArea())).append(System.lineSeparator());

        sb.append(String.format("Lateral Surface Area - %.2f", this.calculateLateralSurfaceArea()))
                .append(System.lineSeparator());

        sb.append(String.format("Volume – %.2f", this.calculateVolume())).append(System.lineSeparator());
        return sb.toString().trim();
    }
}
