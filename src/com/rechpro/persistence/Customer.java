package src.com.rechpro.persistence;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author hmehmeti
 * Kunden Object for DBPersistence
 *
 */
	public class Customer {

		private static long id = 112345;
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;
        private Address address;
        private String telefonNumber = "-";

        //TODO KD where is custom id???
        public Customer(String fName, String lName, String email, Address address) {
        	this.id +=1;
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
            this.address = address;
        }

        public long getId(){
        	return id;
        }

        public String getTelefonNumber(){
        	return telefonNumber;
        }
        public void setTelefonNumber(String newTelNumber){
        	this.telefonNumber = newTelNumber;
        }
		public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String fName) {
            this.firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            this.lastName.set(fName);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String fName) {
            this.email.set(fName);
        }

        public Address getAddress() {
			return address;
		}

        public void setAddress(Address address) {
           this.address = address;
        }

	}
