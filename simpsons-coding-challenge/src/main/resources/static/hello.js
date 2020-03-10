$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8083/character?id=59edee68706374dfa957842f"
    }).then(function(data) {
       $('.myid').append(data.firstName);
       $('.mycontent').append(data.lastName);
    });
});