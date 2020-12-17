package com.yupexx.bazaar.api.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.yupexx.bazaar.api.model.ads.AdPostAnalyticsModel;

public interface AnalyticsInterface {

	List<AdPostAnalyticsModel> getAllAnalyticss();
	Optional getAnalyticsById(Integer AnalyticsId);
	AdPostAnalyticsModel addNewAnalytics(AdPostAnalyticsModel object);
	AdPostAnalyticsModel updateAnalytics(Integer AnalyticsId,AdPostAnalyticsModel object);
	AdPostAnalyticsModel deleteAnalytics(Integer AnalyticsId);
}
