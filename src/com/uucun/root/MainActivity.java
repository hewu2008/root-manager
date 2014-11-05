package com.uucun.root;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qihoo.appstore.R;
import com.qihoo.event.ServiceStartEvent;
import com.qihoo.rtservice.IRTServiceImpl;
import com.qihoo.rtservice.IRootService;
import com.qihoo.rtservice.Utils;

import de.greenrobot.event.EventBus;

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

public class MainActivity extends Activity {
	private TextView mStatusText = null;
	private Button mBrowerApkFileBtn = null;
	private static final String apkfile = "mnt/sdcard/UCDownloads/htc_market.apk";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		setListener();
		startRootService();
	}
	
	private void initView() {
		mStatusText = (TextView)findViewById(R.id.tv_status);
		mBrowerApkFileBtn = (Button)findViewById(R.id.btn_browser_apk_file);
		mBrowerApkFileBtn.setVisibility(View.GONE);
		mStatusText.setText("status:\n");
	}
	
	private void setListener() {
		mBrowerApkFileBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				File file = new File(apkfile);
				IRootService rtService = getRTService();
				if (file.exists() == true && rtService != null) {
					try {
						boolean bRet = rtService.install(apkfile);
						mStatusText.setText("install result:" + bRet + "\n");
					} catch (RemoteException e) {
						e.printStackTrace();
						mStatusText.setText("install result:" + e.getMessage() + "\n");
					}
				}
			}
		});
		EventBus.getDefault().register(this);
	}
	
	private void startRootService() {
		if (Utils.hasSuCmd() && getRTService() == null) {
			mStatusText.append("starting qh_root_service process, please wait...\n");
			new Thread() {
				@Override 
				public void run() {
					List<String> commands = new ArrayList<String>();
					String exportCmd = "export CLASSPATH=$CLASSPATH:" + MainActivity.this.getPackageCodePath();
					commands.add(exportCmd);
					String servicePath = IRTServiceImpl.class.getName();
					String startProcessCmd = String.format("app_process /system/bin %s --nice-name=%s --application &", 
						servicePath, IRTServiceImpl.SERVICE_NAME);
					commands.add(startProcessCmd);
					ServiceStartEvent event = new ServiceStartEvent();
					String result = Utils.execP(commands);
					event.setErrorMessage(result);
					EventBus.getDefault().post(event);
				}
			}.start();
		}else if (getRTService() != null) {
			mBrowerApkFileBtn.setVisibility(View.VISIBLE);
			mStatusText.append("qh_root_service already started.\n");
		} else {
			mStatusText.append("this device does not supported!\n");
		}
	}
	
	public void onEventMainThread(ServiceStartEvent event) {
		if ("success".equals(event.getErrorMessage()) && getRTService() != null) {
			mBrowerApkFileBtn.setVisibility(View.VISIBLE);
			mStatusText.append("start qh_root_service_success!\n");
		}else {
			mStatusText.append("start qh_root_service failed!\n");
		}
    }
	
	public static IRootService getRTService() {
		IBinder binder = ServiceManager.getService(IRTServiceImpl.SERVICE_NAME);
		if (binder != null)
			return IRootService.Stub.asInterface(binder);
		return null;
	}
}
