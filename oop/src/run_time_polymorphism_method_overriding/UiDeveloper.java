package run_time_polymorphism_method_overriding;

public class UiDeveloper extends JobDesignation{
	public UiDeveloper() {
		this.salary = 10000;
	}
	@Override
	public void getSalary() {
		System.out.println("Salary of "+this.getClass().getSimpleName()+" is "+this.salary);
	}
}
