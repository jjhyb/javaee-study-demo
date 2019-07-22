package com.itheima.xml;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class XmlDemo {
	//使用DOM4J读取Book.xml中的数据
	@Test
	public void test1() throws DocumentException{
		//1创建SAXReader解析器对象
		SAXReader sr = new SAXReader();
		//2调用SAXReader对象的read()方法，读取book.xml文件
		Document document = sr.read("src/book.xml");
		//3获得根节点对象
		Element root = document.getRootElement();
		//获取根节点的迭代器对象,获取迭代器就获取了该跟标签的所有子标签
		Iterator it1 = root.elementIterator();
		while(it1.hasNext()){
			//迭代出根标签下所有的子标签
			Element element1 = (Element) it1.next();
			System.out.println(element1.getName());
			//使用迭代器迭代子标签下所有的孙子标签
			Iterator it2 = element1.elementIterator();
			while(it2.hasNext()){
				Element element2 = (Element) it2.next();
				System.out.println(element2.getName());
			}
		}
	}
	
	@Test
	public void test2() throws DocumentException{
		//直接使用方法，遍历所有子标签
		SAXReader sr = new SAXReader();
		//2调用SAXReader对象的read()方法，读取book.xml文件
		Document document = sr.read("src/book.xml");
		//3获得根节点对象
		Element root = document.getRootElement();
		//获取根标签下的所有子标签
		List<Element> list1 = root.elements();
		for (Element element : list1) {
			System.out.println(element.getName());
			//获取element下的所有子标签
			/*List<Element> list2 = element.elements();
			for (Element element2 : list2) {
				//获取标签中的文件
				System.out.println(element2.getText());
			}*/
			
			Element element2 = element.element("书名");
			System.out.println(element2.getText());
		}
	}
	
	@Test
	public void test3() throws DocumentException{
		//获取第一本书的"出版社"属性的属性值!!!
		SAXReader sr = new SAXReader();
		//2调用SAXReader对象的read()方法，读取book.xml文件
		Document document = sr.read("src/book.xml");
		//3获得根节点对象
		Element element = document.getRootElement();
		//获得根节点下的第一个子节点
		Element element2 = element.element("书");
		Attribute attribute = element2.attribute("出版社");
		System.out.println(attribute.getValue());;
	}
	
	@Test
	public void test4() throws DocumentException{
		//获取第二本书的书名
		//XPath,适用于查找某个或者某类标签
		//1.创建SAXReader解析器对象
		SAXReader sr = new SAXReader();
		//2调用SAXReader对象的read()方法，读取book.xml文件
		Document document = sr.read("src/book.xml");
		Node node = document.selectSingleNode("/书架/书[2]/书名");
		System.out.println(node.getText());
	}
	
	@Test
	public void test5() throws DocumentException{
		//查找两本书各自的书名
		//1.创建SAXReader解析器对象
		SAXReader reader = new SAXReader();
		//2调用read方法
		Document document = reader.read("src/book.xml");
		List<Node> list = document.selectNodes("/书架/书/书名");
		for (Node node : list) {
			System.out.println(node.getText());
		}
	}
	
	@Test
	public void test6() throws DocumentException{
		//获取第一本书的"出版社"属性值
		//1.创建SAXReader解析器对象
		SAXReader reader = new SAXReader();
		//2调用read方法
		Document document = reader.read("src/book.xml");
		Node node = document.selectSingleNode("//@出版社");
		System.out.println(node.getStringValue());
	}
	
	
}
