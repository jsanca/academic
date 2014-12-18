
package cr.prodigious.dto.cases;

import java.util.HashMap;

public class CasesDTO extends HashMap<String, Object> implements Entity {


    public  CasesDTO () {}

    public CaseDTO[] Cases() {

        return (CaseDTO[])this.get("Cases");
    }

    public void Cases(CaseDTO[] cases) {

        this.put("Cases", cases);
    }

   /* public List<cr.prodigious.bean.cases.Case> getCases() {

        return (List<cr.prodigious.bean.cases.Case>)this.get("Cases");
    }

    public void setCases(List<cr.prodigious.bean.cases.Case> cases) {

        this.put("Cases", cases);
    }

    public Long getId() {

        return (Long)this.get("Id");
    }

    public void setId(final Long id) {

        this.put("Id", id);
    }
*/
    @Override
    public Long getId() {

        return (Long)this.get("Id");
    }


    public void setId(final Long id) {

        this.put("Id", id);
    }
}
