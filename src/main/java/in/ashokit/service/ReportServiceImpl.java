package in.ashokit.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.entity.EligibilityDtlsEntity;
import in.ashokit.repository.EligDtlsRepository;
import in.ashokit.request.SearchRequest;
import in.ashokit.response.SearchResponse;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private EligDtlsRepository repository;

	@Override
	public List<String> loadPlanNames() {
		List<String> plans = repository.getUniquePlanNames();
		return plans;
	}

	@Override
	public List<String> loadPlanStatuses() {
		List<String> planStatuses = repository.getUniquePlanStatuses();
		return planStatuses;
	}

	@Override
	public List<SearchResponse> searchPlans(SearchRequest request) {
		List<EligibilityDtlsEntity> eligRecords = null;

		if (validateRequest(request)) {
			eligRecords = repository.findAll();

		} else {
			EligibilityDtlsEntity entity = new EligibilityDtlsEntity();

			String planName = request.getPlanName();
			String planStatus = request.getPlanStatus();
			LocalDate startDate = request.getStartDate();
			LocalDate endDate = request.getEndDate();

			if (planName != null && !request.getPlanName().equals("")) {
				entity.setPlanName(planName);
			}
			if (planStatus != null && !request.getPlanStatus().equals("")) {
				entity.setPlanStatus(planStatus);
			}
			if (startDate != null && endDate != null) {
				entity.setStartDate(startDate);
				entity.setEndDate(endDate);
			}

			Example<EligibilityDtlsEntity> of = Example.of(entity);
			eligRecords = repository.findAll(of);
		}

		List<SearchResponse> response = new ArrayList<SearchResponse>();
		for (EligibilityDtlsEntity eligRecord : eligRecords) {
			SearchResponse sr = new SearchResponse();
			BeanUtils.copyProperties(eligRecord, sr);
			response.add(sr);
		}

		return response;
	}

	private boolean validateRequest(SearchRequest request) {
		boolean isEmpty = true;

		if (request.getPlanName() != null && request.getPlanName().equals("")) {
			return isEmpty = false;
		}
		if (request.getPlanStatus() != null && request.getPlanStatus().equals("")) {
			return isEmpty = false;
		}
		if (request.getStartDate() != null && request.getEndDate() != null) {
			return isEmpty = false;
		}
		return isEmpty;
	}

}
