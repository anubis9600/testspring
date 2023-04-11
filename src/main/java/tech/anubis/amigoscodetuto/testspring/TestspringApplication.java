package tech.anubis.amigoscodetuto.testspring;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@RequestMapping("api/v1/customer")
public class TestspringApplication {

	private final CustomerRepository customerRepository;

	public TestspringApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(TestspringApplication.class, args);
	}

	@GetMapping()
	public List<Customer> getCustomers(){
		return customerRepository.findAll();
	}

	record NewCustomerRequest(
		String name,
		String email,
		Integer age
	){}
	// record UpdateCustomerRequest(Integer id, String name, String email, Integer age){
	// }

	@PostMapping
	public void addCustomer(@RequestBody NewCustomerRequest request){
		Customer customer = new Customer();
		customer.setName(request.name);
		customer.setEmail(request.email);
		customer.setAge(request.age);

		customerRepository.save(customer);
	}

	@DeleteMapping("{customerId}")
	public void deleteCustomer(@PathVariable("customerId") Integer id){
		customerRepository.deleteById(id);
	}

	@PutMapping("{customerId}")
	public void updateCustomer(@PathVariable("customerId") Integer id,
								@RequestBody NewCustomerRequest request){

		Customer customer = customerRepository.findById(id).orElse(null);
		customer.setName(request.name);
		customer.setEmail(request.email);
		customer.setAge(request.age);

		customerRepository.save(customer);
	}
	
/* 
	@GetMapping("/")
	public String greet(){
		return "Home Page";
	}

	@GetMapping("/greet")
	public String saygreet(){
		Scanner sc = new Scanner(System.in);

		System.out.println("Entrer votre nom");
		String name = sc.nextLine();
		sc.close();

		return "Welcome Mr(s) "+name+" to our page";
	}

	@GetMapping("/greetWithJson")
	public GreetResponse greetJson(){
		return new GreetResponse("ASYST LTD", 
								new Person("Olivier", 
								27, "moiserukabo@gmail.com", List.of("Maison", "Atelier", "Vehicule")));
	}

	class Person {
		private String name;
		private Integer age;
		private String email;
		private List<String> possession;

		Person(String name, Integer age, String email, List<String> possession){
			this.name = name;
			this.age = age;
			this.email = email;
			this.possession = possession;
		}
		
		public String getName() {
			return this.name;
		}
	
		public Integer getAge() {
			return this.age;
		}
	
		public String getEmail() {
			return this.email;
		}
	
		public List<String> getPossession() {
			return this.possession;
		}

	}

	// class GreetResponse{
	// 	private final String entreprise;
	// 	private Person person;


	// 	GreetResponse(String entreprise, Person person){
	// 		this.entreprise = entreprise;
	// 		this.person = person;
	// 	}

	// 	public String getEntreprise(String entreprise){
	// 		return this.entreprise;
	// 	}
	// 	public Person getPerson(Person person){
	// 		return this.person;
	// 	}
	// }
	
	record GreetResponse(String entreprise, Person person){}


*/
}