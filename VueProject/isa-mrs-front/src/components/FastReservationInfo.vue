<template>
    <div class="card mb-3 border-primary" style="max-width: 18rem;"> 
        <div class="card-body"> 
            <h5 class="card-title"> {{ getDate(action.dateFrom) }} - {{ getDate(action.dateTo) }} </h5>
            <p class="card-text"> Time from: {{ action.timeFrom }} </p>
            <p class="card-text"> Time to: {{ action.timeTo }} </p>
            <p class="card-text"> Price: {{ action.price }} </p>
            <button @click="reserve" class="btn btn-primary"> Reserve </button>
        </div>
    </div>
</template>

<script>
export default {
    props: ["action"],
    methods: {
        getDate (date) {
            let origin_date = new Date(date)
            let month = origin_date.getMonth() + 1
            if (month < 10) {
                month = '0' + month
            }
            return origin_date.getFullYear() + '/' + month + '/' + origin_date.getDate()
        },
        reserve() {
            this.$emit("reserve", this.action);
        }
    }
}
</script>