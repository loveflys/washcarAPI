package com.qgil.washcar.API.utils;

import java.text.SimpleDateFormat;

/**
 * 单例统一管理
 * @author wangjie
 *
 */
public class SingletonManager {
	
	// 定义一个私有构造方法
    private SingletonManager() {}

	//格式为yyyy-MM-dd HH:mm:ss的simpleDateFormat实例(不初始化，不使用final关键字，使用volatile保证了多线程访问时instance变量的可见性，避免了instance初始化时其他变量属性还没赋值完时，被另外线程调用)
	private static volatile SimpleDateFormat sdf;
	//格式为yyyyMMdd的simpleDateFormat实例
    private static volatile SimpleDateFormat dsdf;
	//格式为HHmmss的simpleDateFormat实例
	private static volatile SimpleDateFormat tsdf;
	//格式为yyyy的simpleDateFormat实例
	private static volatile SimpleDateFormat ysdf;
	//格式为mm的simpleDateFormat实例
	private static volatile SimpleDateFormat msdf;
	//格式为mmdd的simpleDateFormat实例
	private static volatile SimpleDateFormat mdsdf;
	
	//返回格式为yyyy-MM-dd HH:mm:ss的simpleDateFormat实例
    public static SimpleDateFormat getIstance() { 
        // 对象实例化时与否判断（不使用同步代码块，instance不等于null时，直接返回对象，提高运行效率）
        if (sdf == null) {
            //同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时对象在第一次创建后，不再重复被创建）
            synchronized (SingletonManager.class) {
                //未初始化，则初始instance变量
                if (sdf == null) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
                }   
            }   
        }   
        return sdf;   
    }
	
	//返回格式为yyyyMMdd的simpleDateFormat实例
    public static SimpleDateFormat getDsdfIstance() { 
        // 对象实例化时与否判断（不使用同步代码块，instance不等于null时，直接返回对象，提高运行效率）
        if (dsdf == null) {
            //同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时对象在第一次创建后，不再重复被创建）
            synchronized (SingletonManager.class) {
                //未初始化，则初始instance变量
                if (dsdf == null) {
                	dsdf = new SimpleDateFormat("yyyy-MM-dd");   
                }   
            }   
        }   
        return dsdf;   
    }
	
    //返回格式为HHmmss的simpleDateFormat实例
    public static SimpleDateFormat getTsdfIstance() { 
        // 对象实例化时与否判断（不使用同步代码块，instance不等于null时，直接返回对象，提高运行效率）
        if (tsdf == null) {
            //同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时对象在第一次创建后，不再重复被创建）
            synchronized (SingletonManager.class) {
                //未初始化，则初始instance变量
                if (tsdf == null) {
                	tsdf = new SimpleDateFormat("HH:mm:ss");   
                }   
            }   
        }   
        return tsdf;   
    }

    //返回格式为yyyy的simpleDateFormat实例
	public static SimpleDateFormat getYsdfInstance() {
		// 对象实例化时与否判断（不使用同步代码块，instance不等于null时，直接返回对象，提高运行效率）
        if (ysdf == null) {
            //同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时对象在第一次创建后，不再重复被创建）
            synchronized (SingletonManager.class) {
                //未初始化，则初始instance变量
                if (ysdf == null) {
                	ysdf = new SimpleDateFormat("yyyy");   
                }   
            }   
        }   
        return ysdf; 
	}
	
	//返回格式为MM的simpleDateFormat实例
	public static SimpleDateFormat getMsdfInstance() {
		// 对象实例化时与否判断（不使用同步代码块，instance不等于null时，直接返回对象，提高运行效率）
        if (msdf == null) {
            //同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时对象在第一次创建后，不再重复被创建）
            synchronized (SingletonManager.class) {
                //未初始化，则初始instance变量
                if (msdf == null) {
                	msdf = new SimpleDateFormat("MM");   
                }   
            }   
        }   
        return msdf; 
	}

	//返回格式为MMdd的simpleDateFormat实例
	public static SimpleDateFormat getMDsdfInstance() {
		// 对象实例化时与否判断（不使用同步代码块，instance不等于null时，直接返回对象，提高运行效率）
        if (mdsdf == null) {
            //同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时对象在第一次创建后，不再重复被创建）
            synchronized (SingletonManager.class) {
                //未初始化，则初始instance变量
                if (mdsdf == null) {
                	mdsdf = new SimpleDateFormat("MM月dd日");   
                }   
            }   
        }   
        return mdsdf; 
	}
}
