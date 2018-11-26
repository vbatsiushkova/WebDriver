package page;

import page.AbstractPage;

public class AbstractUIPage extends AbstractPage {

	protected void visitUiPage(String path) {
		goToUrl(getLocaleUrl(path));
	}

	protected void initializePage() {
		setUrl("");
	}


	protected void setUrl(String url) {
		this.url = url;
	}

	private String getLocaleUrl(String path) {
		return path;
	}

	public String getUrlFormat() {
		return urlFormat;
	}

}
