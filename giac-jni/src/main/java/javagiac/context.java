/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 1.3.40
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package javagiac;

public class context {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected context(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(context obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  @Override
protected void finalize() {
    System.err.println("context.java: ignoring finalize()");
    // delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        giacJNI.delete_context(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setTabptr(SWIGTYPE_p_giac__sym_tab value) {
    giacJNI.context_tabptr_set(swigCPtr, this, SWIGTYPE_p_giac__sym_tab.getCPtr(value));
  }

  public SWIGTYPE_p_giac__sym_tab getTabptr() {
    long cPtr = giacJNI.context_tabptr_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_giac__sym_tab(cPtr, false);
  }

  public void setGlobalcontextptr(context value) {
    giacJNI.context_globalcontextptr_set(swigCPtr, this, context.getCPtr(value), value);
  }

  public context getGlobalcontextptr() {
    long cPtr = giacJNI.context_globalcontextptr_get(swigCPtr, this);
    return (cPtr == 0) ? null : new context(cPtr, false);
  }

  public void setPrevious(context value) {
    giacJNI.context_previous_set(swigCPtr, this, context.getCPtr(value), value);
  }

  public context getPrevious() {
    long cPtr = giacJNI.context_previous_get(swigCPtr, this);
    return (cPtr == 0) ? null : new context(cPtr, false);
  }

  public void setGlobalptr(SWIGTYPE_p_giac__global value) {
    giacJNI.context_globalptr_set(swigCPtr, this, SWIGTYPE_p_giac__global.getCPtr(value));
  }

  public SWIGTYPE_p_giac__global getGlobalptr() {
    long cPtr = giacJNI.context_globalptr_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_giac__global(cPtr, false);
  }

  public void setParent(context value) {
    giacJNI.context_parent_set(swigCPtr, this, context.getCPtr(value), value);
  }

  public context getParent() {
    long cPtr = giacJNI.context_parent_get(swigCPtr, this);
    return (cPtr == 0) ? null : new context(cPtr, false);
  }

  public void setQuoted_global_vars(SWIGTYPE_p_giac__vecteur value) {
    giacJNI.context_quoted_global_vars_set(swigCPtr, this, SWIGTYPE_p_giac__vecteur.getCPtr(value));
  }

  public SWIGTYPE_p_giac__vecteur getQuoted_global_vars() {
    long cPtr = giacJNI.context_quoted_global_vars_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_giac__vecteur(cPtr, false);
  }

  public void setHistory_in_ptr(SWIGTYPE_p_giac__vecteur value) {
    giacJNI.context_history_in_ptr_set(swigCPtr, this, SWIGTYPE_p_giac__vecteur.getCPtr(value));
  }

  public SWIGTYPE_p_giac__vecteur getHistory_in_ptr() {
    long cPtr = giacJNI.context_history_in_ptr_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_giac__vecteur(cPtr, false);
  }

  public void setHistory_out_ptr(SWIGTYPE_p_giac__vecteur value) {
    giacJNI.context_history_out_ptr_set(swigCPtr, this, SWIGTYPE_p_giac__vecteur.getCPtr(value));
  }

  public SWIGTYPE_p_giac__vecteur getHistory_out_ptr() {
    long cPtr = giacJNI.context_history_out_ptr_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_giac__vecteur(cPtr, false);
  }

  public context() {
    this(giacJNI.new_context__SWIG_0(), true);
  }

  public context(context c) {
    this(giacJNI.new_context__SWIG_1(context.getCPtr(c), c), true);
  }

  public context(String name) {
    this(giacJNI.new_context__SWIG_2(name), true);
  }

  @Override
public context clone() {
    long cPtr = giacJNI.context_clone(swigCPtr, this);
    return (cPtr == 0) ? null : new context(cPtr, false);
  }

}
