package org.pepello.mappers;

import org.mapstruct.Mapper;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.dto.dashboard.Dashboard;
import org.pepello.dto.dashboard.DtoDashboard;

@Mapper(componentModel = "spring")
public interface DashboardMapper extends BaseMapper<Dashboard, DtoDashboard> {
}
