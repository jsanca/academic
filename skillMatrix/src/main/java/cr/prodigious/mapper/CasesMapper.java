package cr.prodigious.mapper;

import cr.prodigious.bean.cases.CasesBean;
import cr.prodigious.dto.cases.CaseDTO;
import cr.prodigious.dto.cases.CasesDTO;

public class CasesMapper implements Mapper<CasesDTO, CasesBean> {

    private static CaseMapper caseMapper = new CaseMapper();
    private static ListArrayMapper listArrayMapper = new ListArrayMapper();

    /**
     * Map a Bean to Entity
     *
     * @param casesBean B
     * @return E
     */
    @Override
    public CasesDTO map(CasesBean casesBean) {

        final CasesDTO casesDTO = new CasesDTO();
        final int casesSizes = casesBean.getCases().size();

        casesDTO.Cases();
        casesDTO.setId(casesBean.getId());
        casesDTO.Cases
                (listArrayMapper.map(casesBean.getCases(),
                        new CaseDTO [casesSizes],
                        caseMapper));

        return casesDTO;
    }

    /**
     * Map a Entity to Bean
     *
     * @param casesDTO E
     * @return B
     */
    @Override
    public CasesBean map(CasesDTO casesDTO) {
        return null;
    }
} // E:O:F:CasesMapper
