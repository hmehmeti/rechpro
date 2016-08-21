/**
 *
 */
package com.rechpro.persistence;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author hmehmeti
 * Kunden Object for DBPersistence
 *
 */
	public class Kunde {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;
        private final SimpleStringProperty address;

        public Kunde(String fName, String lName, String email, String address) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
            this.address = new SimpleStringProperty(address);
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

        public SimpleStringProperty getAddress() {
			return address;
		}

        public void setAddress(String address) {
           this.address.set(address);
        }

	}
