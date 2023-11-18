const { exec } = require('child_process');

exports.preCommit = (props) => {
    let mvnVersionSet = "mvn versions:set -DnewVersion=" + props.version;
    let mvnCommit = "mvn versions:commit";
    let gitCommit = "git commit -am \"chore: bump project version to " + props.version + " [skip ci]\"";

    exec(mvnVersionSet, (err, stdout, stderr) => {
        if (err) {
            console.log(err);
            return;
        }
        console.log(stdout);
        exec(mvnCommit, (err, stdout, stderr) => {
            if (err) {
                console.log(err);
                return;
            }
            console.log(stdout);
            exec(gitCommit, (err, stdout, stderr) => {
                if (err) {
                    console.log(err);
                    return;
                }
                console.log(stdout);
            });
        });
    });

}