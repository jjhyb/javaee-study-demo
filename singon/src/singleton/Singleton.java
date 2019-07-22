package singleton;

public class Singleton {
	private Singleton() {
		System.out.println("LazySingleton  is  create"); // 创建单例的过程可能会比较慢
	}

	public static class SingletonHolder {
		private static final Singleton instance = new Singleton();
	}

	public static synchronized Singleton getInstance() {
		return SingletonHolder.instance;
	}

	/*
	 * 没错，这就是现在最完整的单例写法，内部类实现单例，除了effctive java中阐述的枚举单例实现，这种方法是目前最好的实现方式。
	 * 在这个实现中，用内部类来保护单例，当Singleton类被加载时，内部类不会被初始化，所以可以确保Singleton类被载入JVM时，
	 * 不会初始化单例类，当getInstance方法被调用时，才会加载SingleHolder，从而初始化instance，同时，
	 * 由于实例的建立是在类加载时完成的，故天生对多线程友好，getInstance（）方法也不需要使用synchronized修饰，因此，
	 * 这种实现能兼顾前两种写法的优点（延迟加载，非同步）。
	 * 最后，在极端情况下，序列化和反序列化可能会破坏单例，一般来说不多见，如果存在就要多加注意，此时可以加入以下代码：
	 */
	/*private Object readResolve() {
		return instance;
	}*/

}
