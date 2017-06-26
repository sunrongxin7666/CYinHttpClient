package com.android.srx.github.dbgenerator;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by sunrongxin on 2017/6/25.
 */

@Entity
public class DownloadEntity {
	@Id(autoincrement = true)
	private Long id;
	private Long start_position;
	private Long end_position;
	private Long progress_position;

	private String download_url;

	private int thread_id;

	@Generated(hash = 1541426413)
	public DownloadEntity(Long id, Long start_position, Long end_position,
			Long progress_position, String download_url, int thread_id) {
		this.id = id;
		this.start_position = start_position;
		this.end_position = end_position;
		this.progress_position = progress_position;
		this.download_url = download_url;
		this.thread_id = thread_id;
	}

	@Generated(hash = 1671715506)
	public DownloadEntity() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStart_position() {
		return this.start_position;
	}

	public void setStart_position(Long start_position) {
		this.start_position = start_position;
	}

	public Long getEnd_position() {
		return this.end_position;
	}

	public void setEnd_position(Long end_position) {
		this.end_position = end_position;
	}

	public Long getProgress_position() {
		return this.progress_position;
	}

	public void setProgress_position(Long progress_position) {
		this.progress_position = progress_position;
	}

	public String getDownload_url() {
		return this.download_url;
	}

	public void setDownload_url(String download_url) {
		this.download_url = download_url;
	}

	public int getThread_id() {
		return this.thread_id;
	}

	public void setThread_id(int thread_id) {
		this.thread_id = thread_id;
	}
}
