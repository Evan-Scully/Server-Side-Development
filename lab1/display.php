<?php
$player = $_POST['eintracht'];
?>

<head>
    <link rel="stylesheet" href="style.css">
</head>

<html>
<body>
    <div class="nav">
        <div class="logo">
            <a href="index.html"><img src="logo.png"></a>
        </div>
        <ol>
            <li><a href="https://www.eintracht.de/">Home</a></li>
            <li><a href="content.html">Team</a></li>
            <li><a href="team.html">Form</a></li>
        </ol>
    </div>
        <?php echo $player; ?></br>
    </body>
</html>