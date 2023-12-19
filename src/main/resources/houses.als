module houses

abstract sig House {
    major: one Major,
    color: one Color,
}

one sig H1, H2, H3 extends House {}

enum Major {
    Math, Phil, CS
}

enum Color {
    Blue, Red, White
}

fact {
    // all majors are in/linked by some house
    Major in House.major
    // all colors are on/used by some house
    Color in House.color
    
    // The philosophy major lives directly to the right of the red house.
    (H1.color = Red and H2.major = Phil) or ((H2.color = Red and H3.major = Phil))
    //The computer science major lives in the blue house.
    some h : House | h.color = Blue and h.major = CS
    //The math major lives in house 2.
    H2.major = Math    
}

run {} 