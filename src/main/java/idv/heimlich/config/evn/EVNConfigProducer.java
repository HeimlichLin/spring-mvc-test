package idv.heimlich.config.evn;

public class EVNConfigProducer {
	
	/**
	 * 取得設定檔案
	 */
	public static IEVNConfig getConfig() {
		return getFactory(EVNSource.DEF_PROPERTIES).getConfig();
	}

	/**
	 * 取得設定檔案工廠
	 */
	public static EVNConfigFactory getFactory() {
		return getFactory(EVNSource.DEF_PROPERTIES);
	}
	
	public static EVNConfigFactory getFactory(EVNSource type) {
		return type.getFactory();
	}

}
