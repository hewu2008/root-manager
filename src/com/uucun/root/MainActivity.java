/**
 * Copyright (C) 2014-2015 hewu <hewu2008@gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.uucun.root;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.qihoo.appstore.R;
import com.qihoo.constant.Constants;
import com.qihoo.permmgr.PermManager;
import com.qihoo.rtservice.IRTServiceImpl;
import com.qihoo.rtservice.IRootService;
import com.qihoo.rtservice.Utils;
import com.qihoo.sharestore.SharedStore;
import com.qihoo.utils.FileUtils;

public class MainActivity extends Activity {
	private TextView mStatusText = null;
	private Button mBrowerApkFileBtn = null;
	private Handler mHandler = null;
	private static final int FILE_SELECT_CODE = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		setListener();
		new RootAsyncTask().execute();
	}
	
	/**
	 * 初始化界面
	 */
	private void initView() {
		mStatusText = (TextView) findViewById(R.id.tv_status);
		mBrowerApkFileBtn = (Button) findViewById(R.id.btn_browser_apk_file);
		mBrowerApkFileBtn.setVisibility(View.GONE);
		mStatusText.setText("status:\n");
	}
	
	/**
	 * 设置监听器
	 */
	private void setListener() {
		mHandler = new Handler() {
			public void handleMessage(Message msg) {
				String text = String.valueOf(msg.obj);
				mStatusText.append(text);
				super.handleMessage(msg);
			}
		};
		mBrowerApkFileBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				IRootService rtService = getRTService();
				if (rtService != null) {
					showFileChooser();
				} else {
					Toast.makeText(MainActivity.this,
							"root process not started.", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});
	}
	
	/**
	 * 设置当前的状态
	 */
	public void setStatus(String status) {
		Message msg = new Message();
		msg.what = 0;
		msg.obj = status;
		mHandler.sendMessage(msg);
	}
	
	/**
	 * 执行ROOT方案
	 */
	private int doRootSolution() {
		if (getRTService() != null) {
			return Constants.ROOT_SUCCESS;
		}
		PermManager permManager = PermManager.getInstance(this);
		setStatus("trying to save env_file.");
		permManager.saveEnv();
		SharedStore store = new SharedStore(this, Constants.SOLUTION_FILE);
		String md5 = store.getString(Constants.KEY_SOLUTION_MD5, "");
		if (TextUtils.isEmpty(md5)) {
			setStatus("trying to do local solution...\n");
			if (permManager.doSolutionLocal() != Constants.ROOT_SUCCESS) {
				setStatus("trying to do online solution...\n");
				return permManager.doSolutionOnline(this);
			} else {
				return Constants.ROOT_SUCCESS;
			}
		} else {
			setStatus("trying to do success solution...\n");
			return permManager.doSuccessSolution(md5);
		}
	}

	private void startRootProcess() {
		if (Utils.hasSuCmd() && getRTService() == null) {
			mStatusText.append("starting root process, please wait...\n");
			new StartProcessAsyncTask().execute();
		} else if (getRTService() != null) {
			mBrowerApkFileBtn.setVisibility(View.VISIBLE);
			mStatusText.append("root process already started.\n");
		} else {
			mStatusText.append("does not supported this device.\n");
		}
	}

	public static IRootService getRTService() {
		IBinder binder = ServiceManager.getService(IRTServiceImpl.SERVICE_NAME);
		if (binder != null)
			return IRootService.Stub.asInterface(binder);
		return null;
	}

	private void showFileChooser() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("application/vnd.android.package-archive");
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		try {
			startActivityForResult(
					Intent.createChooser(intent, "select apk file"),
					FILE_SELECT_CODE);
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(this, "Please install a File Manager.",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case FILE_SELECT_CODE:
			if (resultCode == RESULT_OK) {
				Uri uri = data.getData();
				final String path = FileUtils.getPath(this, uri);
				Log.d(Constants.TAG, "path:" + path);
				if (path == null)
					return;
				File file = new File(path);
				IRootService rtService = getRTService();
				if (file.exists() == true && rtService != null) {
					new InstallPackageAsyncTask().execute(path);
				}
			}
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * ROOT异步任务
	 */
	private class RootAsyncTask extends AsyncTask<Void, Void, Integer> {
		@Override
		protected void onPreExecute() {
			mStatusText.append("do Rooting solution...\n");
		}

		@Override
		protected Integer doInBackground(Void... params) {
			return doRootSolution();
		}

		@Override
		protected void onPostExecute(Integer result) {
			mStatusText.append("do Rooting result: " + result + ".\n");
			startRootProcess();
		}
	}

	private class StartProcessAsyncTask extends AsyncTask<Void, Void, String> {
		@Override
		protected String doInBackground(Void... params) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (getRTService() != null) 
				return "success";
			else 
				return "false";
		}

		@Override
		protected void onPostExecute(String result) {
			if ("success".equals(result)) {
				mBrowerApkFileBtn.setVisibility(View.VISIBLE);
				mStatusText.append("start root process success\n");
			} else {
				mStatusText.append("start root process failed\n");
			}
		}
	}

	private class InstallPackageAsyncTask extends
			AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute() {
			mStatusText.append("installing...\n");
		}

		@Override
		protected String doInBackground(String... params) {
			IRootService rtService = getRTService();
			boolean bRet = false;
			try {
				bRet = rtService.install(params[0]);
			} catch (RemoteException e) {
				e.printStackTrace();
				return e.getMessage();
			}
			if (bRet == true) {
				return "success";
			} else {
				return "failed";
			}
		}

		@Override
		protected void onPostExecute(String result) {
			mStatusText.append("install result: " + result + "\n");
		}
	}
}
