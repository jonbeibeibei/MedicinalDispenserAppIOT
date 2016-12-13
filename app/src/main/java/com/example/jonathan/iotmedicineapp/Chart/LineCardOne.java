package com.example.jonathan.iotmedicineapp.Chart;

import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.db.chart.Tools;
import com.db.chart.animation.Animation;
import com.db.chart.animation.easing.BounceEase;
import com.db.chart.model.LineSet;
import com.db.chart.renderer.AxisRenderer;
import com.db.chart.tooltip.Tooltip;
import com.db.chart.view.LineChartView;
import com.example.jonathan.iotmedicineapp.R;
import com.example.jonathan.iotmedicineapp.Utilities.Status;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


public class LineCardOne extends CardController {

	double dadMissed;
	float chartnum;

	public void getDad() {
		FirebaseDatabase chartDatabase = FirebaseDatabase.getInstance();
		DatabaseReference chartRef = chartDatabase.getReference("Parents/Dad/missed");
		chartRef.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String s) {
				Status status = dataSnapshot.getValue(Status.class);
				dadMissed = status.averageMissed();
			}

			@Override
			public void onChildChanged(DataSnapshot dataSnapshot, String s) {

			}

			@Override
			public void onChildRemoved(DataSnapshot dataSnapshot) {

			}

			@Override
			public void onChildMoved(DataSnapshot dataSnapshot, String s) {

			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});
//		chartRef.addValueEventListener(new ValueEventListener() {
//			@Override
//			public void onDataChange(DataSnapshot dataSnapshot) {
//				Status status = dataSnapshot.getValue(Status.class);
//				dadMissed = status.averageMissed();
//			}
//
//			@Override
//			public void onCancelled(DatabaseError databaseError) {
//
//			}
//		});
	}


	public void getNum(){
		int day;
		getDad();
		if (dadMissed == 0){
			chartnum = 0;
		}
		else if(dadMissed == 30){
			chartnum = 33.3f;
		}
		else if(dadMissed == 60){
			chartnum = 66.6f;
		}
		else if(dadMissed == 100){
			chartnum = 100f;
		}

		day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

		if (day == 1){
			mValues[0][0] = chartnum;
		}
		else if (day == 2){
			mValues[0][1] = chartnum;
		}
		else if (day == 3){
			mValues[0][2] = chartnum;
		}
		else if (day == 4){
			mValues[0][3] = chartnum;
		}
		else if (day == 5){
			mValues[0][4] = chartnum;
		}
		else if (day == 6){
			mValues[0][5] = chartnum;
		}
		else if (day == 0){
			mValues[0][6] = chartnum;
		}

	}


	private final LineChartView mChart;


	private final Context mContext;


	private final String[] mLabels = {"Mon","Tues","Weds","Thurs","Fri","Sat","Sun"};

	private final float[][] mValues = {{80.0f, 0.0f,10f,20f,30f,40f,50f},{10.0F,40.0f}};


	private Tooltip mTip;

	private Runnable mBaseAction;


	public LineCardOne(CardView card, Context context) {

		super(card);

		mContext = context;
		mChart = (LineChartView) card.findViewById(R.id.chart1);
	}


	@Override
	public void show(Runnable action) {

		super.show(action);

		// Tooltip
		mTip = new Tooltip(mContext, R.layout.linechart_three_tooltip, R.id.value);

		((TextView) mTip.findViewById(R.id.value)).setTypeface(
				Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Semibold.ttf"));

		mTip.setVerticalAlignment(Tooltip.Alignment.BOTTOM_TOP);
		mTip.setDimensions((int) Tools.fromDpToPx(58), (int) Tools.fromDpToPx(25));

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {

			mTip.setEnterAnimation(PropertyValuesHolder.ofFloat(View.ALPHA, 1),
					PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f),
					PropertyValuesHolder.ofFloat(View.SCALE_X, 1f)).setDuration(200);

			mTip.setExitAnimation(PropertyValuesHolder.ofFloat(View.ALPHA, 0),
					PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f),
					PropertyValuesHolder.ofFloat(View.SCALE_X, 0f)).setDuration(200);

			mTip.setPivotX(Tools.fromDpToPx(65) / 2);
			mTip.setPivotY(Tools.fromDpToPx(25));
		}

		mChart.setTooltips(mTip);

		// Data
//		LineSet dataset = new LineSet(mLabels, mValues[0]);
//		dataset.setColor(Color.parseColor("#758cbb"))
//				  .setFill(Color.parseColor("#2d374c"))
//				  .setDotsColor(Color.parseColor("#758cbb"))
//				  .setThickness(4)
//				  .setDashed(new float[] {10f, 10f})
//				  .beginAt(0);
//		mChart.addData(dataset);

		LineSet dataset = new LineSet(mLabels, mValues[0]);
		dataset.setColor(Color.parseColor("#b3b5bb"))
				.setFill(Color.parseColor("#2d374c"))
				.setDotsColor(Color.parseColor("#ffc755"))
				.setThickness(4)
				.endAt(7);
		mChart.addData(dataset);

		// Chart
		mChart.setBorderSpacing(Tools.fromDpToPx(15))
				.setAxisBorderValues(0, 20)
				.setYLabels(AxisRenderer.LabelPosition.NONE)
				.setLabelsColor(Color.parseColor("#6a84c3"))
				.setXAxis(true)
				.setAxisBorderValues(0,110)
				.setYAxis(true);

		mBaseAction = action;
		Runnable chartAction = new Runnable() {
			@Override
			public void run() {

				mBaseAction.run();
				mTip.prepare(mChart.getEntriesArea(0).get(3), mValues[0][3]);
				mChart.showTooltip(mTip, true);
			}
		};

		Animation anim = new Animation().setEasing(new BounceEase()).setEndAction(chartAction);

		mChart.show(anim);
	}


	@Override
	public void update() {
		super.update();

		mChart.dismissAllTooltips();
//		if (firstStage) {
//			mChart.updateValues(0, mValues[1]);
//			mChart.updateValues(1, mValues[1]);
//		} else {
		mChart.updateValues(0, mValues[0]);
//			mChart.updateValues(1, mValues[0]);
//		}


		mChart.getChartAnimation().setEndAction(mBaseAction);
		mChart.notifyDataUpdate();
	}


	@Override
	public void dismiss(Runnable action) {

		super.dismiss(action);

		mChart.dismissAllTooltips();
		mChart.dismiss(new Animation().setEasing(new BounceEase()).setEndAction(action));
	}

}
