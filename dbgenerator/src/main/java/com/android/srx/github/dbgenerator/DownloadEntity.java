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
	private long id;
	private long start_position;
	private long end_position;
	private long progress_postion;

	private String download_url;

	private int thread_id;

	@Generated(hash = 274730861)
	public DownloadEntity(long id, long start_position, long end_position,
			long progress_postion, String download_url, int thread_id) {
		this.id = id;
		this.start_position = start_position;
		this.end_position = end_position;
		this.progress_postion = progress_postion;
		this.download_url = download_url;
		this.thread_id = thread_id;
	}

	@Generated(hash = 1671715506)
	public DownloadEntity() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getStart_position() {
		return this.start_position;
	}

	public void setStart_position(long start_position) {
		this.start_position = start_position;
	}

	public long getEnd_position() {
		return this.end_position;
	}

	public void setEnd_position(long end_position) {
		this.end_position = end_position;
	}

	public long getProgress_postion() {
		return this.progress_postion;
	}

	public void setProgress_postion(long progress_postion) {
		this.progress_postion = progress_postion;
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
