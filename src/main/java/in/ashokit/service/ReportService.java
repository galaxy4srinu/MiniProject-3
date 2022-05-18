package in.ashokit.service;

import java.util.List;

import in.ashokit.request.SearchRequest;
import in.ashokit.response.SearchResponse;

public interface ReportService {

	public List<String> loadPlanNames();

	public List<String> loadPlanStatuses();

	public List<SearchResponse> searchPlans(SearchRequest request);
	

}
