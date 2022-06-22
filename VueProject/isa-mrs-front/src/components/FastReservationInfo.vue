<template>
    <div class="card mb-3 border-primary" style="max-width: 18rem;"> 
        <div class="card-body"> 
            <h5 class="card-title"> {{ getDate(action.dateFrom) }} - {{ getDate(action.dateTo) }} </h5>
            <p class="card-text"> Time from: {{ action.timeFrom }} </p>
            <p class="card-text"> Time to: {{ action.timeTo }} </p>
            <p class="card-text"> Max number of people: {{ action.maxNumOfPeople }} </p>
            <p class="card-text"> Price: {{ action.price }}<span>&#8364;</span> </p>
            <button @click="reserve" class="btn btn-primary"> Reserve </button>
        </div>
    </div>
</template>

<script>
export default {
    props: ["action"],
    methods: {
        getDate(date) {
                let origin_date = new Date(date)
                let month = origin_date.getMonth() + 1
                let day = origin_date.getDate()
                if (month < 10) {
                    month = '0' + month
                }
                if (day < 10) {
                    day = '0' + day
                }
                return origin_date.getFullYear() + '/' + month + '/' + day
            },
        reserve() {
            this.$emit("reserve", this.action);
        }
    }
}
</script>