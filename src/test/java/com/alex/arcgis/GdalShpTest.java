package com.alex.arcgis;

import org.gdal.ogr.*;
import org.gdal.ogr.Driver;
import org.junit.Test;
import org.gdal.gdal.*;

public class GdalShpTest {
	@Test
	public void shp2Json() {

		// ע�����е�����
		ogr.RegisterAll();
		// Ϊ��֧������·�������������������
		gdal.SetConfigOption("GDAL_FILENAME_IS_UTF8", "YES");
		// Ϊ��ʹ���Ա��ֶ�֧�����ģ�������������
		gdal.SetConfigOption("SHAPE_ENCODING", "");

		//String strVectorFile = "F:\\Alex\\Desktop\\�����ļ�\\һ��\\ʡ��������\\ʡ��������.shp";

//		String strVectorFile ="F:\\Alex\\Desktop\\�����ļ�\\һ��\\����\\����.shp";
		
		String strVectorFile ="F:\\Alex\\Desktop\\�����ļ�\\�ϸ�\\���سɹ�\\GDJC2017\\GDJC2017.shp";
		// String strVectorFile ="F:\\Alex\\Desktop\\�����ļ�\\һ��\\����1\\����1.shp";
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

		System.out.println(str);
		System.out.println(ds.GetDescription());
		System.out.println("ת���ɹ���");

	}
}