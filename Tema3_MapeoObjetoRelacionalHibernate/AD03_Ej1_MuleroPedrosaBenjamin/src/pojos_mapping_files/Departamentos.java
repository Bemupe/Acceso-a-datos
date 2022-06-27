package pojos_mapping_files;
// Generated 12-ene-2022 21:59:34 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Departamentos generated by hbm2java
 */
public class Departamentos  implements java.io.Serializable {


     private byte deptNo;
     private String dnombre;
     private String loc;
     private Set empleadoses = new HashSet(0);

    public Departamentos() {
    }

	
    public Departamentos(byte deptNo) {
        this.deptNo = deptNo;
    }
    public Departamentos(byte deptNo, String dnombre, String loc, Set empleadoses) {
       this.deptNo = deptNo;
       this.dnombre = dnombre;
       this.loc = loc;
       this.empleadoses = empleadoses;
    }
   
    public byte getDeptNo() {
        return this.deptNo;
    }
    
    public void setDeptNo(byte deptNo) {
        this.deptNo = deptNo;
    }
    public String getDnombre() {
        return this.dnombre;
    }
    
    public void setDnombre(String dnombre) {
        this.dnombre = dnombre;
    }
    public String getLoc() {
        return this.loc;
    }
    
    public void setLoc(String loc) {
        this.loc = loc;
    }
    public Set getEmpleadoses() {
        return this.empleadoses;
    }
    
    public void setEmpleadoses(Set empleadoses) {
        this.empleadoses = empleadoses;
    }




}


