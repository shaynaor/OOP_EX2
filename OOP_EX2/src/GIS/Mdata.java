package GIS;

import java.util.Arrays;
import java.util.Date;

import Geom.Point3D;
/**
 * This class represents the metadata we get from a csv file.
 * @author A Beast
 *
 */
public class Mdata implements Meta_data {
	private String MAC;
	private String SSID;
	private String AuthMode;
	private String FirstSeen;
	private String Channel;
	private String RSSI;
	private String AccuracyMeters;
	private String Type;



	public Mdata(String[] meta) {
		this.MAC = meta[0];
		this.SSID = meta[1];
		this.AuthMode = meta[2];
		this.FirstSeen = meta[3];
		this.Channel = meta[4];
		this.RSSI = meta[5];
		this.AccuracyMeters = meta[6];
		this.Type = meta[7];

	}

	public String getMAC() {
		return MAC;
	}

	public String getSSID() {
		return SSID;
	}

	public String getAuthMode() {
		return AuthMode;
	}

	public String getFirstSeen() {
		return FirstSeen;
	}

	public String getChannel() {
		return Channel;
	}

	public String getRSSI() {
		return RSSI;
	}

	public String getAccuracyMeters() {
		return AccuracyMeters;
	}

	public String getType() {
		return Type;
	}

	public long getUTC() {
		Long currTime = new Date().getTime();
		return currTime;
	}
	

	public Point3D get_Orientation() {/// *****not need to do.******
		return null;
	}

}
