package com.alex.arcgis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.gdal.gdal.gdal;
import org.gdal.ogr.DataSource;
import org.gdal.ogr.Driver;
import org.gdal.ogr.ogr;
import org.junit.Test;

public class NewFeatureLayer {

	@Test
	public void loadShpFileToJson() {
		// ע�����е�����
		ogr.RegisterAll();
		// Ϊ��֧������·�������������������
		gdal.SetConfigOption("GDAL_FILENAME_IS_UTF8", "YES");
		// Ϊ��ʹ���Ա��ֶ�֧�����ģ�������������
		gdal.SetConfigOption("SHAPE_ENCODING", "");

		String strVectorFile = "F://Alex//Desktop//�����ļ�//һ��//����3//����3.shp";

		// String strVectorFile =
		// "//Users//apple//Downloads//GDJCWY2017-1//GDJCWY2017-1.shx";
		// ���ļ�
		DataSource ds = ogr.Open(strVectorFile, 0);
		if (ds == null) {
			System.out.println("���ļ�ʧ�ܣ�");
			return;
		}
		System.out.println("���ļ��ɹ���");
		Driver dv = ogr.GetDriverByName("GeoJSON");
		if (dv == null) {
			System.out.println("������ʧ�ܣ�");
			return;
		}
		System.out.println("�������ɹ���");
		// System.out.println(dv.GetMetadataDomainList());

		String str = strVectorFile.substring(0, strVectorFile.length() - 4) + ".json";

		System.out.println(str);

		dv.CopyDataSource(ds, str);

		System.out.println("ת���ɹ���");

		System.out.println(readToString(str));
	}

	public String readToString(String fileName) {
		String encoding = "gbk";
		File file = new File(fileName);
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return new String(filecontent, encoding);
		} catch (UnsupportedEncodingException e) {
			System.err.println("The OS does not support " + encoding);
			e.printStackTrace();
			return null;
		}
	}

	@Test
	public void uploadToArcGISServer() {
		// FIXME ���ݲ�֪��δ����ŵ�ͼ��
		String src = readToString("F://Alex//Desktop//�����ļ�//һ��//����//����.json");
	}

}
