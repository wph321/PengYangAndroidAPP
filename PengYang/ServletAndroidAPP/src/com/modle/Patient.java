package com.modle;

import java.io.Serializable;
import java.util.Date;

public class Patient implements Serializable {

	private int id;
	private String name;
	private String sex;
	private int age;
	private String shenqingxu;
	private String menzhenhao;
	private String zhuyuanhao;
	private String idhao;
	private String jianchashijian;
	private String keshi;
	private String xiangmu;
	private String laoyuan;
	private String baogao;

	
	
	public String getXiangmu() {
		return xiangmu;
	}

	public void setXiangmu(String xiangmu) {
		this.xiangmu = xiangmu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getShenqingxu() {
		return shenqingxu;
	}

	public void setShenqingxu(String shenqingxu) {
		this.shenqingxu = shenqingxu;
	}

	public String getMenzhenhao() {
		return menzhenhao;
	}

	public void setMenzhenhao(String menzhenhao) {
		this.menzhenhao = menzhenhao;
	}

	public String getZhuyuanhao() {
		return zhuyuanhao;
	}

	public void setZhuyuanhao(String zhuyuanhao) {
		this.zhuyuanhao = zhuyuanhao;
	}

	public String getIdhao() {
		return idhao;
	}

	public void setIdhao(String idhao) {
		this.idhao = idhao;
	}

	public String getJianchashijian() {
		return jianchashijian;
	}

	public void setJianchashijian(String jianchashijian) {
		this.jianchashijian = jianchashijian;
	}

	public String getKeshi() {
		return keshi;
	}

	public void setKeshi(String keshi) {
		this.keshi = keshi;
	}

	public String getLaoyuan() {
		return laoyuan;
	}

	public void setLaoyuan(String laoyuan) {
		this.laoyuan = laoyuan;
	}

	public String getBaogao() {
		return baogao;
	}

	public void setBaogao(String baogao) {
		this.baogao = baogao;
	}

	public Patient(int id, String name, String sex, int age, String shenqingxu, String menzhenhao, String zhuyuanhao,
			String idhao, String jianchashijian, String keshi, String xiangmu, String laoyuan, String baogao) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.shenqingxu = shenqingxu;
		this.menzhenhao = menzhenhao;
		this.zhuyuanhao = zhuyuanhao;
		this.idhao = idhao;
		this.jianchashijian = jianchashijian;
		this.keshi = keshi;
		this.xiangmu = xiangmu;
		this.laoyuan = laoyuan;
		this.baogao = baogao;
	}

	public Patient() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", sex=" + sex + ", age=" + age + ", shenqingxu=" + shenqingxu
				+ ", menzhenhao=" + menzhenhao + ", zhuyuanhao=" + zhuyuanhao + ", idhao=" + idhao + ", jianchashijian="
				+ jianchashijian + ", keshi=" + keshi + ", xiangmu=" + xiangmu + ", laoyuan=" + laoyuan + ", baogao="
				+ baogao + "]";
	}

	
	
}
