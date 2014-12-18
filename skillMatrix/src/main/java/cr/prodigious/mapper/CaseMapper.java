package cr.prodigious.mapper;

import cr.prodigious.bean.cases.Case;
import cr.prodigious.bean.cases.CasesBean;
import cr.prodigious.dto.cases.CaseDTO;
import cr.prodigious.dto.cases.CasesDTO;

public class CaseMapper implements Mapper<CaseDTO, Case> {

    /**
     * Map a Bean to Entity
     *
     * @param aCase B
     * @return E
     */
    @Override
    public CaseDTO map(Case aCase) {

        final CaseDTO caseDTO = new CaseDTO();

        caseDTO.setId(aCase.getId());
        caseDTO.setBrand(aCase.getBrand());
        caseDTO.setDescription(aCase.getDescription());
        caseDTO.setExcecutionTime(aCase.getExcecutionTime());
        caseDTO.setImage(aCase.getImage());
        caseDTO.setInputText(aCase.getInputText());

        return caseDTO;
    }

    /**
     * Map a Entity to Bean
     *
     * @param caseDTO E
     * @return B
     */
    @Override
    public Case map(CaseDTO caseDTO) {

        final Case aCase = new Case();

        aCase.setId(caseDTO.getId());
        aCase.setBrand(caseDTO.getBrand());
        aCase.setDescription(caseDTO.getDescription());
        aCase.setExcecutionTime(caseDTO.getExcecutionTime());
        aCase.setImage(caseDTO.getImage());
        aCase.setInputText(caseDTO.getInputText());


        return aCase;
    }
} // E:O:F:CasesMapper
