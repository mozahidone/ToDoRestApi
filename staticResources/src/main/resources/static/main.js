function main() {
    console.log("nicks-cors-test");
    $.ajax
    ({
        url: "http://127.0.0.1:3001/dwss-ws/user-management/api/v1/privileges/user/current",
        headers: {
            "X-API-Key": "sample-api-key",
            "X-Request-ID": "1234567890"
        },
        success: function (data) {
            alert("Success!");
            console.log(data);
        }
    });
}
