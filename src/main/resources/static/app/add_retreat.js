Vue.component('add-retreat', {
    template: `
        <form class="w-50 mx-auto mt-5">
            <h2> New retreat </h2>
                <div class="form-group mb-3">
                    <label>Name</label>
                    <input v-model="form.name" type="text" class="form-control"/>
                    <p v-if="!nameIsValid" class="alert alert-danger">Name is required.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Description</label>
                    <textarea v-model="form.description" type="text" class="form-control"/>
                    <p v-if="!descriptionIsValid" class="alert alert-danger">Description is required.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Country</label>
                    <input v-model="form.country" type="text" class="form-control"/>
                    <p v-if="!countryIsValid" class="alert alert-danger">Country is required, and it starts with first capital letter.</p>
                </div>
                <div class="form-group mb-3">
                    <label>City</label>
                    <input v-model="form.city" type="text" class="form-control"/>
                    <p v-if="!cityIsValid" class="alert alert-danger">City is required, and it starts with first capital letter.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Address</label>
                    <input v-model="form.address" type="text" class="form-control"/>
                    <p v-if="!addressIsValid" class="alert alert-danger">Address is required.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Number of rooms</label>
                    <input v-model.number="form.numOfRooms" type="number" class="form-control"/>
                    <p v-if="!roomsIsValid" class="alert alert-danger">Number of rooms is required, and it must be number greater than 0.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Number of beds</label>
                    <input v-model.number="form.numOfBeds" type="number" class="form-control"/>
                    <p v-if="!bedsIsValid" class="alert alert-danger">Number of beds is required, and it must be number greater than 0.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Rules of conduction:</label>
                    <br>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.rulesOfConduction" value="Inside smoking disallowed"/>
                        <label>Inside smoking disallowed</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.rulesOfConduction" value="Pet friendly"/>
                        <label>Pet friendly</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.rulesOfConduction" value="Parties disallowed"/>
                        <label>Parties disallowed</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.rulesOfConduction" value="Furniture braking is charged extra"/>
                        <label>Furniture braking is charged extra</label>
                    </div>
                </div>
                <div class="form-group mb-3">
                    <label>Additional services:</label>
                    <br>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.additionalServices" value="WiFi"/>
                        <label>WiFi</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.additionalServices" value="Garage"/>
                        <label>Garage</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.additionalServices" value="Heating"/>
                        <label>Heating</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.additionalServices" value="Kitchen"/>
                        <label>Kitchen</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.additionalServices" value="Shower"/>
                        <label>Shower</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.additionalServices" value="Kitchen"/>
                        <label>Kitchen</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.additionalServices" value="Barbeque"/>
                        <label>Barbeque</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.additionalServices" value="Drinking water"/>
                        <label>Drinking water</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.additionalServices" value="Bathroom"/>
                        <label>Bathroom</label>
                    </div>
                </div>
                <div class="form-group mb-3">
                    <label>Pictures:</label>
                    <input accept="image/*" type="file" class="form-control" @change="addPicture($event)"/>
                </div>
                <div class="form-group mb-3">
                    <button @click="addRetreat" type="submit" class="btn btn-primary float-end">Add retreat</button>
                </div>
        </form>

    `,

    data() {
        return {
            form: {
                name: null,
                description: null,
                country: null,
                city: null,
                address: null,
                numOfRooms: null,
                numOfBeds: null,
                rulesOfConduction: [],
                additionalServices: [],
                pictures: []
            }
        }
    },

    computed: {
        nameIsValid() {
            return !!this.form.name;
        },
        descriptionIsValid() {
            return !!this.form.description;
        },
        countryIsValid() {
            return RegExp(/([A-Z]{1})([a-z]+)([^0-9]*)$/).test(this.form.country) && !!this.form.country;
        },
        cityIsValid() {
            return RegExp(/([A-Z]{1})([a-z]+)([^0-9]*)$/).test(this.form.city) && !!this.form.city;
        },
        addressIsValid() {
            return !!this.form.address;
        },
        roomsIsValid() {
            return typeof this.form.numOfRooms === 'number' && !!this.form.numOfRooms && this.form.numOfRooms > 0;
        },
        bedsIsValid() {
            return typeof this.form.numOfBeds === 'number' && !!this.form.numOfBeds && this.form.numOfBeds > 0;
        },
        formIsValid() {
            return this.nameIsValid && this.descriptionIsValid && this.countryIsValid && this.cityIsValid && this.addressIsValid && this.roomsIsValid && this.bedsIsValid;
        }
    },

    methods: {

        addRetreat() {
            if (this.formIsValid) {
                console.log(this.form.rulesOfConduction);
                console.log(this.form.pictures)
                console.log("Dobri su parametri za vikendicu.");
            }
        },

        addPicture(e) {
            let files = e.target.files || e.dataTransfer.files;
            if (!files.length)
                return;
            this.form.pictures.push(files[0].name);
        }
    }
});