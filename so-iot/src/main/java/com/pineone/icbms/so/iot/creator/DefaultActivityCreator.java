package com.pineone.icbms.so.iot.creator;

import java.util.ArrayList;
import java.util.List;

import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.iot.resources.person.DefaultStudent;
import com.pineone.icbms.so.iot.resources.vo.pd.DefaultPhysicalDevice;
import com.pineone.icbms.so.resources.action.IGenericAction;
import com.pineone.icbms.so.resources.activity.DefaultActivity;
import com.pineone.icbms.so.resources.activity.IGenericActivity;
import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.model.repo.activity.DefaultActivityModel;
import com.pineone.icbms.so.resources.processor.IActivityCreator;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;

import javax.validation.constraints.Null;

/**
 * Created by existmaster on 2016. 1. 11..
 */
public class DefaultActivityCreator implements
		IActivityCreator<IGenericActivity, DefaultActivityModel, IGenericContext>
{

	/**
	 * Create Activities.
	 * 
	 * @param activityModelList
	 *            activityModelList
	 * @return List of IGenericActivity
	 */
	@Override
	public List<IGenericActivity> createActivities(
			List<DefaultActivityModel> activityModelList,
			IGenericContext context)
	{
		List<IGenericActivity> activityList = new ArrayList<IGenericActivity>();
		for (DefaultActivityModel activityModel : activityModelList)
		{
			activityList.add(createActivity(activityModel, context));
		}
		return activityList;
	}

	/**
	 * Create Activity.
	 * 
	 * @param activityModel
	 *            activityModel
	 * @return IGenericActivity
	 */
	@Override
	public IGenericActivity createActivity(DefaultActivityModel activityModel,
			IGenericContext context)
	{
		DefaultActionCreator defaultActionCreator = new DefaultActionCreator();
		List<IGenericAction> actions = null;
		try
		{
			actions = defaultActionCreator
					.createActions(activityModel.getActionModelList());
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		DefaultActivity activity = new DefaultActivity();

		// TODO : activity <-- contextUtil copy

		try
		{
			activity.addValue(IotServiceContext.ACTION_TARGET_LOCATION,
					context.getValue(IotServiceContext.ACTION_TARGET_LOCATION));
		}
		catch (NullPointerException ignored)
		{
		}

		try
		{
			activity.addValue(IotServiceContext.ACTION_TARGET_STUDENT,
					context.getValue(IotServiceContext.ACTION_TARGET_STUDENT));
		}
		catch (NullPointerException ignored)
		{
		}

		activity.setId(activityModel.getId() + "_" + System.nanoTime());
		activity.setName(activityModel.getName());
		activity.setActionList(actions);

		// //TempDevices
		// List<DefaultPhysicalDevice> deviceList = new ArrayList<>();
		// DefaultPhysicalDevice defaultPhysicalDevice = new
		// DefaultPhysicalDevice();
		// defaultPhysicalDevice.setUri("http://www.pineone.com/herit-in/herit-cse/Humidifier_LR0001HU0001");
		// deviceList.add(defaultPhysicalDevice);
		//
		// DefaultPhysicalDevice defaultPhysicalDevice2 = new
		// DefaultPhysicalDevice();
		// defaultPhysicalDevice2.setUri("http://www.pineone.com/herit-in/herit-cse/Humidifier_LR0001HU0002");
		// deviceList.add(defaultPhysicalDevice2);
		//
		// activity.addValue(IotServiceContext.ACTION_DEVICE_URI, deviceList);
		// //end

		activity.addValue(IotServiceContext.ACTION_ONTOLOGY_REFERENCE,
				activityModel.getOntologyReference());
		activity.addValue(IotServiceContext.ACTION_OPERATION_VALUE,
				activityModel.getOperationValue());
		return activity;
	}
}
