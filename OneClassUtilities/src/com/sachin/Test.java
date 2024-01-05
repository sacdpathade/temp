package com.sachin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

class Vote implements Comparable<Vote>{
    char team;
    List<Integer> voteOrder;

    Vote(char team) {
        this.team = team;
        voteOrder = new LinkedList<>();
    }

    public char getTeam() {
        return team;
    }

    public void addVote(int order) {
        voteOrder.add(order);
    }

    public int getVoteCount() {
        int voteValue = 0;
        Iterator<Integer> iterator = voteOrder.iterator();

        while(iterator.hasNext()) {
            voteValue += (iterator.next());
        }
        return voteValue;
    }

    @Override
    public int compareTo(Vote o) {
        if(o == null) {
            return -1;
        }

        if(this.getVoteCount() < o.getVoteCount()) {
            return -1;
        }

        if(this.getVoteCount() > o.getVoteCount()) {
            return 1;
        }

        if(this.voteOrder.size() > o.voteOrder.size() ) {
            return -1;
        }
        if(this.voteOrder.size() < o.voteOrder.size() ) {
            return 1;
        }

        Collections.sort(voteOrder);
        Collections.sort(o.voteOrder);

        for(int index=0; index < voteOrder.size(); index++) {
            if(this.voteOrder.get(index) < o.voteOrder.get(index)) {
                return -1;
            }
            if(this.voteOrder.get(index) > o.voteOrder.get(index)) {
                return 1;
            }
        }

        return 0;
    }


}

public class Test {



    public String rankTeams(String[] votes) {

        Map<Character, Vote> teamVoteDetails = new HashMap<>();

        for(String vote : votes) {
            for(int index=0; index < vote.length(); index++) {
                Character team = vote.charAt(index);
                Vote teamVote = teamVoteDetails.get(team);
                if(teamVote == null) {
                    teamVote = new Vote(team);
                    teamVoteDetails.put(team, teamVote);
                }
                teamVote.addVote(index);
            }
        }

        SortedSet<Vote> voteSet = new TreeSet<>();
        voteSet.addAll(teamVoteDetails.values());

        StringBuffer response = new StringBuffer();
        for(Vote vote : voteSet) {
            response.append(vote.team);
        }

        
        return response.toString();
    }

    public static void main(String[] args) {
        Test tes = new Test();
        String[] votes = {"ABC","ACB","ABC","ACB","ACB"};
        System.out.println(tes.rankTeams(votes));
        String[] votestwo = {"ZMNAGUEDSJYLBOPHRQICWFXTVK"};
        System.out.println(tes.rankTeams(votestwo));
    }
    
}