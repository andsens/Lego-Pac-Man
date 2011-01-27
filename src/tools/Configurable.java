package tools;

public interface Configurable {
	public void increaseSetting(int setting);
	public void decreaseSetting(int setting);
	public String getSettingValue(int setting);
}
